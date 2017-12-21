package com.os.accounts

import com.os.accounts.domain.Account
import spock.lang.Shared
import spock.lang.Specification

class BaseSpecification extends Specification {

  def setup() {
    0 * _
  }

  @Shared
  Account account1 = new Account(accountId: '1', accountDesc: 'accDesc1', userId: 'abc1', passwordHint: 'xxx###', pinIdHint: '1234', url: 'www.fake.com')

  @Shared
  Account account2 = new Account(accountId: '2', accountDesc: 'accDesc2', userId: 'abc2', passwordHint: 'yyy###', pinIdHint: '5678', url: 'www.test.com')
}
