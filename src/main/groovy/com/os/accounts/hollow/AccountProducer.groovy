package com.os.accounts.hollow

import com.netflix.hollow.api.consumer.HollowConsumer.AnnouncementWatcher
import com.netflix.hollow.api.consumer.HollowConsumer.BlobRetriever
import com.netflix.hollow.api.consumer.fs.HollowFilesystemAnnouncementWatcher
import com.netflix.hollow.api.producer.HollowIncrementalProducer
import com.netflix.hollow.api.producer.HollowProducer
import com.netflix.hollow.api.producer.HollowProducer.Announcer
import com.netflix.hollow.api.producer.HollowProducer.BlobStorageCleaner
import com.netflix.hollow.api.producer.HollowProducer.Publisher
import com.netflix.hollow.tools.compact.HollowCompactor
import com.os.accounts.domain.Account
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import java.util.concurrent.Executors

@Slf4j
@Component
class AccountProducer extends HollowHealthCheck {

  HollowIncrementalProducer hollowIncrementalProducer
  HollowProducer hollowProducer

  private boolean firstExecution = false

  private static final long TYPE_SHARDING_SIZE = 25 * 1024 * 1024 //25 MB

  private int cyclesToTriggerCompaction = 10
  private int cyclesBeforeCompaction = 0

  public long currentVersion = 0

  @Autowired
  AccountHollowConfig hollowConfig

  @Autowired
  Publisher publisher

  @Autowired
  Announcer announcer

  @Autowired
  BlobStorageCleaner blobStorageCleaner

  @Autowired
  BlobRetriever blobRetriever

  boolean isReady = false

  @PostConstruct
  void init() {
    int statesBetweenSnapshots = hollowConfig.numStatesBetweenSnapshots
    AccountHollowProducerListener producerListener = new AccountHollowProducerListener()

    hollowProducer = HollowProducer.withPublisher(publisher)
        .withAnnouncer(announcer)
        .withListener(producerListener)
        .withNumStatesBetweenSnapshots(statesBetweenSnapshots)
        .withSnapshotPublishExecutor(Executors.newSingleThreadExecutor())
        .withTargetMaxTypeShardSize(TYPE_SHARDING_SIZE)
        .withBlobStorageCleaner(blobStorageCleaner)
        .build()

    try {
      restoreFromExistingState()
    } catch (Exception e) {
      log.error("Couldn't initialize from existing state", e)
      firstExecution = true
    }

    hollowIncrementalProducer = new HollowIncrementalProducer(hollowProducer)
    isReady = true
  }

  /**
   * Restore from existing state
   */
  void restoreFromExistingState() {
    long latestAnnouncedVersion
    try {
      AnnouncementWatcher announcementWatcher = getAnnouncementWatcher()
      hollowProducer.initializeDataModel(Account)
      latestAnnouncedVersion = announcementWatcher.latestVersion
      if (!latestAnnouncedVersion)
        throw new RuntimeException("Could not find announced version")
    } catch (Exception e) {
      firstExecution = true
      log.error("Previous state not available", e)
      return
    }

    try {
      hollowProducer.initializeDataModel(Account)
      hollowProducer.restore(latestAnnouncedVersion, blobRetriever)
    } catch (Exception e) {
      log.error("could not restore from previous state!", e)
      throw e
    }
  }

  /**
   * Gets the proper announcement watcher.
   * Filesystem if using spring local profile
   * @return
   */
  private getAnnouncementWatcher() {
    AnnouncementWatcher announcementWatcher
    if (System.getProperty("spring.profiles.active") == 'local') {
      File publishDir = new File(AccountHollowConfig.SCRATCH_DIR, "publish-dir")
      publishDir.mkdir()
      announcementWatcher = new HollowFilesystemAnnouncementWatcher(publishDir)
    } else {
//      announcementWatcher = new GCSAnnouncementWatcher(hollowConfig)
    }
    return announcementWatcher
  }

  void publishAccount(Account account) {
    cyclesBeforeCompaction++
    try {
      long cycle = firstExecution ? executeFirstCycle(account) : executeIncrementalCycle(account)
      currentVersion = cycle
    } catch (Exception e) {
      log.error("something went wrong while publishing aisle locations", e)
    } finally {
      checkAndExecuteCompaction()
    }
  }

  void checkAndExecuteCompaction() {
    try {
      if (cyclesBeforeCompaction == cyclesToTriggerCompaction) {
        cyclesBeforeCompaction = 0
        log.info("Running compaction cycle")
        long compactionResult = hollowProducer.runCompactionCycle(
            new HollowCompactor.CompactionConfig(0, 10)
        )
        log.info("Finished compaction cycle")
        log.info("Compaction was executed: ${(Long.MIN_VALUE != compactionResult)}")
      }
    } catch (Exception e) {
      log.error("Could not run compaction | ${e.message}", e)
    }
  }

  int executeFirstCycle(Account account) {
    log.info("Executing first cycle")
    int finalVersion = hollowProducer.runCycle({ state ->
      log.info("Adding initial ${account} to hollow")
      state.add(account)
    })
    firstExecution = false
    return finalVersion
  }

  int executeIncrementalCycle(Account account) {
    log.info("Executing incremental cycle")
    log.info("Adding incremental ${account} to hollow")
    hollowIncrementalProducer.addOrModify(account)
    log.info "Initializing cycle"
    int finalVersion = hollowIncrementalProducer.runCycle()
    firstExecution = false
    return finalVersion
  }

  @Override
  boolean isReady() {
    return isReady
  }

}