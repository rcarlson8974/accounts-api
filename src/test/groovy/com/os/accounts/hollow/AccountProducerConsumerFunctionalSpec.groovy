package com.os.accounts.hollow

import com.netflix.hollow.api.consumer.HollowConsumer
import com.netflix.hollow.api.consumer.fs.HollowFilesystemAnnouncementWatcher
import com.netflix.hollow.api.consumer.fs.HollowFilesystemBlobRetriever
import com.netflix.hollow.api.producer.fs.HollowFilesystemAnnouncer
import com.netflix.hollow.api.producer.fs.HollowFilesystemBlobStorageCleaner
import com.netflix.hollow.api.producer.fs.HollowFilesystemPublisher
import com.os.accounts.BaseSpecification
import com.os.accounts.domain.Account
import com.os.accounts.transformer.AccountTransformer
import spock.lang.Shared

class AccountProducerConsumerFunctionalSpec extends BaseSpecification {

  @Shared
  AccountHollowConfig hollowConfig = new AccountHollowConfig(
      domain: "account-producer-test-${UUID.randomUUID().toString()}",
      bucket: "os-data",
      applicationName: "account-producer-test",
      namespace: "account-producer-test",
      numOfSnapshotsToKeep: 5,
      numStatesBetweenSnapshots: 40
  )

  File publishDir = new File(AccountHollowConfig.SCRATCH_DIR, "publish-dir")
  HollowConsumer.AnnouncementWatcher announcementWatcher = new HollowFilesystemAnnouncementWatcher(publishDir)
  HollowConsumer.BlobRetriever blobRetriever = new HollowFilesystemBlobRetriever(publishDir)
  AccountConsumer consumer = new AccountConsumer(announcementWatcher: announcementWatcher, blobRetriever: blobRetriever)
  AccountProducer producer = new AccountProducer(  publisher: new HollowFilesystemPublisher(publishDir),
      announcer: new HollowFilesystemAnnouncer(publishDir),
      blobRetriever: new HollowFilesystemBlobRetriever(publishDir),
      blobStorageCleaner: new HollowFilesystemBlobStorageCleaner(publishDir, 2),
      hollowConfig: hollowConfig,
      firstExecution: true
  )

  def setup() {
    publishDir.mkdir()
    producer.init()
  }

  def 'can product and consume accounts'() {

    setup:
    List<Account> accounts = [account1, account2]
    accounts.each { Account account ->
      producer.publishAccount(account)
    }
    sleep(1000) // need to wait for the Announcement to happen and the Watcher to pick it up.....
    consumer.init()

    when:
    def foundAccounts = consumer.getAccounts()

    then:
    foundAccounts.size() == 2
    def transformedAccounts = AccountTransformer.transformFromHollowToDomains(foundAccounts as List<com.os.accounts.domain.generated.Account>)
    transformedAccounts.containsAll([account1, account2])

    cleanup:
    publishDir.delete()

  }

}
