package com.os.accounts.hollow

import com.netflix.hollow.api.consumer.HollowConsumer
import com.netflix.hollow.api.custom.HollowAPI
import com.os.accounts.BaseSpecification
import com.os.accounts.domain.generated.AccountAPI
import com.os.accounts.domain.generated.AccountPrimaryKeyIndex

class AccountConsumerSpec extends BaseSpecification {

  HollowConsumer.AnnouncementWatcher announcementWatcher = Mock()
  HollowConsumer.BlobRetriever blobRetriever = Mock()
  AccountPrimaryKeyIndex idx = Mock()
  HollowConsumer hollowConsumer = Mock()
  AccountAPI accountAPI = Mock()
  AccountConsumer consumer = new AccountConsumer(
      announcementWatcher: announcementWatcher,
      blobRetriever: blobRetriever,
      idx: idx,
      consumer: hollowConsumer)

  def 'gets wired'() {
    when:
    def hollowConsumer = new HollowConsumerTest()

    then:
    !hollowConsumer.isReady()
  }

  def 'finds match'() {

    when:
    def account = consumer.findMatch(account1.accountId)

    then:
    idx.findMatch(account1.accountId) >> mockHollowAccount1
    account == mockHollowAccount1
  }

  def 'gets accounts'() {
    when:
    def accounts = consumer.getAccounts()

    then:
    1 * hollowConsumer.getAPI() >> accountAPI
    1 * accountAPI.getAllAccount() >> [mockHollowAccount1, mockHollowAccount2]
    accounts == [mockHollowAccount1, mockHollowAccount2]
  }

  static class HollowConsumerTest extends AccountConsumer {}

}
