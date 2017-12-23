package com.os.accounts.hollow

import com.netflix.hollow.api.consumer.HollowConsumer
import com.netflix.hollow.api.consumer.fs.HollowFilesystemAnnouncementWatcher
import com.netflix.hollow.api.consumer.fs.HollowFilesystemBlobRetriever
import com.netflix.hollow.api.producer.HollowProducer
import com.netflix.hollow.api.producer.fs.HollowFilesystemAnnouncer
import com.netflix.hollow.api.producer.fs.HollowFilesystemBlobStorageCleaner
import com.netflix.hollow.api.producer.fs.HollowFilesystemPublisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@Configuration
class AccountHollowConfig {

  @Value('${hollow.bucket}')
  String bucket

  @Value('${hollow.namespace}')
  String namespace

  @Value('${hollow.domain}')
  String domain

  @Value('${hollow.applicationName}')
  String applicationName

  @Value('${hollow.numStatesBetweenSnapshots:30}')
  int numStatesBetweenSnapshots

  @Value('${hollow.numOfSnapshotsToKeep:15}')
  Integer numOfSnapshotsToKeep

  public static final String SCRATCH_DIR = System.getProperty("java.io.tmpdir")

  @Bean
  HollowProducer.Publisher getPublisher() {
    File publishDir = new File(SCRATCH_DIR, "publish-dir")
    publishDir.mkdir()
    return new HollowFilesystemPublisher(publishDir)
  }

  @Bean
  HollowProducer.Announcer getLocalAnnouncer() {
    File publishDir = new File(SCRATCH_DIR, "publish-dir")
    publishDir.mkdir()
    return new HollowFilesystemAnnouncer(publishDir)
  }

  @Bean
  HollowProducer.BlobStorageCleaner getBlobStorage(@Autowired AccountHollowConfig hollowConfig) {
    File publishDir = new File(SCRATCH_DIR, "publish-dir")
    publishDir.mkdir()
    return new HollowFilesystemBlobStorageCleaner(publishDir, hollowConfig.numOfSnapshotsToKeep)
  }

  @Bean
  HollowConsumer.BlobRetriever getBlobRetriever(@Autowired AccountHollowConfig hollowConfig) {
    File publishDir = new File(SCRATCH_DIR, "publish-dir")
    return new HollowFilesystemBlobRetriever(publishDir, null)
  }

  @Bean
  AccountConsumer getConsumer() {
    return new AccountConsumer()
  }

  @Bean
  HollowConsumer.AnnouncementWatcher getAnnouncementWatcher() {
    File publishDir = new File(SCRATCH_DIR, "publish-dir")
    publishDir.mkdir()
    return new HollowFilesystemAnnouncementWatcher(publishDir)
  }

}
