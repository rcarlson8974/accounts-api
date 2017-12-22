package com.os.accounts.service

import com.os.accounts.BaseSpecification
import com.os.accounts.domain.Account
import com.os.accounts.hollow.AccountConsumer
import com.os.accounts.hollow.AccountProducer
import com.os.accounts.transformer.AccountTransformer

class AccountServiceSpec extends BaseSpecification {

  AccountConsumer consumer = Mock(AccountConsumer)
  AccountProducer producer = Mock(AccountProducer)
  AccountService accountService = new AccountService(consumer: consumer, producer: producer)

  def 'gets accounts'() {
    setup:
    GroovyMock(AccountTransformer, global: true)
    com.os.accounts.domain.generated.Account mockAccount = Mock(com.os.accounts.domain.generated.Account)

    when:
    List<Account> accounts = accountService.getAccounts()

    then:
    1 * consumer.isReady() >> true
    1 * consumer.getAccounts() >> [mockAccount]
    1 * AccountTransformer.transformFromHollowToDomains([mockAccount]) >> [account1]
    accounts == [account1]
  }

  def 'gets account'() {
    setup:
    GroovyMock(AccountTransformer, global: true)
    com.os.accounts.domain.generated.Account mockAccount = Mock(com.os.accounts.domain.generated.Account)

    when:
    Account account = accountService.getAccount(accountId)

    then:
    1 * consumer.isReady() >> true
    1 * consumer.findMatch(accountId) >> mockAccount
    1 * AccountTransformer.transformFromHollowToDomain(mockAccount) >> account1
    account == account1
  }

  def 'creates account'() {
    setup:
    GroovyMock(AccountTransformer, global: true)
    com.os.accounts.domain.generated.Account mockAccount = Mock(com.os.accounts.domain.generated.Account)

    when:
    Account account = accountService.createAccount(account1)

    then:
    1 * producer.publishAccount(account1)
    account == account1
  }

//  def 'gets in aisle locations not found'() {
//    when:
//    List<AisleLocation> aisleLocations = aisleLocationService.getAisleLocations(storeId, aisleId1)
//
//    then:
//    1 * hollowConsumer.getProperty('indexReady') >> true
//    1 * hollowConsumer.findAisleLocationMatches(storeId, aisleId1) >> null
//    !aisleLocations
//  }
//
//  def 'gets in aisle locations consumer is not ready'() {
//    when:
//    List<AisleLocation> aisleLocations = aisleLocationService.getAisleLocations(storeId, aisleId1)
//
//    then:
//    1 * hollowConsumer.getProperty('indexReady') >> false
//    0 * hollowConsumer.findAisleLocationMatches(storeId, aisleId1)
//    !aisleLocations
//  }

}
