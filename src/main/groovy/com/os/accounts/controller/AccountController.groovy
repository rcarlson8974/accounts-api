package com.os.accounts.controller

import com.os.accounts.domain.Account
import com.os.accounts.service.AccountService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@CompileStatic
class AccountController {

  @Autowired
  AccountService accountsService

  @RequestMapping(value = '/v1/accounts', method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  ResponseEntity<List<Account>> getAccounts() {
    try {
      List<Account> accounts = accountsService.getAccounts()
      return new ResponseEntity(accounts ?: null, !accounts || accounts.empty ? HttpStatus.NOT_FOUND : HttpStatus.OK)
    } catch (e) {
      String errMsg = "error trying to get accounts -> ${e.message ?: e}"
      log.error(errMsg, e)
      return new ResponseEntity(errMsg, HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @RequestMapping(value = '/v1/accounts/{accountId}', method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  ResponseEntity<List<Account>> getAccountn(@PathVariable String accountId) {
    try {
      Account account = accountsService.getAccount(accountId)
      return new ResponseEntity(account ?: null, !account ? HttpStatus.NOT_FOUND : HttpStatus.OK)
    } catch (e) {
      String errMsg = "error trying to get account ${accountId} -> ${e.message ?: e}"
      log.error(errMsg, e)
      return new ResponseEntity(errMsg, HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @RequestMapping(value = '/v1/accounts/create', method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  ResponseEntity<Account> getAccountn(@RequestBody Account account) {
    try {
      Account savedAccount = accountsService.createAccount(account)
      return new ResponseEntity(savedAccount, HttpStatus.OK)
    } catch (e) {
      String errMsg = "error trying to create account ${account} -> ${e.message ?: e}"
      log.error(errMsg, e)
      return new ResponseEntity(errMsg, HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

}