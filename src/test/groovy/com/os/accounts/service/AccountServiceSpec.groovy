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

    def 'gets accounts not found'() {
      when:
      List<Account> accounts = accountService.getAccounts()

      then:
      1 * consumer.isReady() >> true
      1 * consumer.getAccounts() >> null
      0 * AccountTransformer.transformFromHollowToDomains(_)
      !accounts
  }

  def 'gets accounts consumer is not ready'() {
    when:
    List<Account> accounts = accountService.getAccounts()

    then:
    1 * consumer.isReady() >> false
    0 * consumer.getAccounts()
    0 * AccountTransformer.transformFromHollowToDomains(_)
    !accounts
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

  def 'gets account not found'() {
    when:
    Account account = accountService.getAccount(accountId)

    then:
    1 * consumer.isReady() >> true
    1 * consumer.findMatch(accountId) >> null
    0 * AccountTransformer.transformFromHollowToDomain(_)
    account == null
  }

  def 'gets account consumer is not ready'() {
    when:
    Account account = accountService.getAccount(accountId)

    then:
    1 * consumer.isReady() >> false
    0 * consumer.findMatch(accountId)
    0 * AccountTransformer.transformFromHollowToDomain(_)
    !account
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

}
