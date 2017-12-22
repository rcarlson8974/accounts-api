package com.os.accounts.controller

import com.os.accounts.BaseSpecification
import com.os.accounts.service.AccountService
import org.springframework.http.HttpStatus
import spock.lang.Unroll

class AccountControllerSpec extends BaseSpecification {

  AccountService accountService = Mock(AccountService)
  AccountController accountController = new AccountController(accountService: accountService)

  def 'gets account'() {

    when:
    def response = accountController.getAccount(accountId)

    then:
    1 * accountService.getAccount(accountId) >> account1

    and:
    response.statusCode == HttpStatus.OK
    response.body == account1
  }

  @Unroll
  def 'gets accounts - #description'() {

    when:
    def response = accountController.getAccounts()

    then:
    1 * accountService.getAccounts() >> accounts

    and:
    response.statusCode == expectedStatusCode
    response.body == expectedResponseBody

    where:
    description              | accounts             | expectedStatusCode   | expectedResponseBody
    'happy path'             | [account1, account2] | HttpStatus.OK        | [account1, account2]
    'null service response'  | null                 | HttpStatus.NOT_FOUND | null
    'empty service response' | []                   | HttpStatus.NOT_FOUND | null
  }

  def 'gets accounts handles exception'() {

    when:
    def response = accountController.getAccounts()

    then:
    1 * accountService.getAccounts() >> {
      throw new Exception("get accounts blew up")
    }
    response.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
    response.body == "error trying to get accounts -> get accounts blew up"
  }

}
