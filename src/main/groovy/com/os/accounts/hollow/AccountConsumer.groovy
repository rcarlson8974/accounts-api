package com.os.accounts.hollow

import com.netflix.hollow.api.consumer.HollowConsumer
import com.os.accounts.domain.generated.Account
import com.os.accounts.domain.generated.AccountAPI
import com.os.accounts.domain.generated.AccountPrimaryKeyIndex
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct

@Slf4j
class AccountConsumer extends HollowHealthCheck {

  @Autowired
  HollowConsumer.AnnouncementWatcher announcementWatcher

  @Autowired
  HollowConsumer.BlobRetriever blobRetriever

  HollowConsumer consumer
  AccountPrimaryKeyIndex idx

  boolean isReady = false

  @PostConstruct
  void init() {
    consumer = HollowConsumer.withBlobRetriever(blobRetriever)
        .withAnnouncementWatcher(announcementWatcher)
        .withGeneratedAPIClass(AccountAPI.class)
        .build()

    consumer.triggerRefresh()

    while (!isReady) {
      try {
        idx = new AccountPrimaryKeyIndex(consumer, "accountId")
        isReady = true
        log.info("index ${idx} is ready")
      } catch (Exception e) {
        sleep(1000)
      }
    }

  }

  Account findMatch(String accountId) {
    return idx.findMatch(accountId)
  }

  Collection<Account> getAccounts() {
    AccountAPI api = consumer.API as AccountAPI
    return api.getAllAccount() as List
  }

  @Override
  boolean isReady() {
    return isReady
  }

}
