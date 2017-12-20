package com.os.accounts.hollow

import com.netflix.hollow.api.consumer.HollowConsumer
import com.os.accounts.domain.generated.Account
import com.os.accounts.domain.generated.AccountAPI
import com.os.accounts.domain.generated.AccountPrimaryKeyIndex
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct

@Slf4j
class AccountHollowConsumer {

//  @Autowired
//  HollowConsumer.AnnouncementWatcher announcementWatcher
//
//  @Autowired
//  HollowConsumer.BlobRetriever blobRetriever
//
//  HollowConsumer consumer
//  AccountPrimaryKeyIndex idx
//
//  boolean indexReady = false
//
//  @PostConstruct
//  void init() {
//    AccountHollowMetricsCollector metricsCollector = new AccountHollowMetricsCollector()
//
//    consumer = HollowConsumer.withBlobRetriever(blobRetriever)
//        .withAnnouncementWatcher(announcementWatcher)
//        .withRefreshListener(new AccountHollowRefreshListener())
//        .withGeneratedAPIClass(AccountAPI.class)
//        .withMetricsCollector(metricsCollector)
//        .build()
//
//    consumer.triggerRefresh()
//
//    while (!indexReady) {
//      try {
//        idx = new AccountPrimaryKeyIndex(consumer, "accountId")
//        indexReady = true
//        log.info("index ${idx} is ready")
//      } catch (Exception e) {
//        sleep(1000)
//      }
//    }
//
//  }
//
//  Account findMatch(String accountId) {
//    return idx.findMatch(accountId)
//  }
//
//  List<Account> getAccounts() {
//    AccountAPI api = consumer.API as AccountAPI
//    return api.getAllAccount()
//  }

}