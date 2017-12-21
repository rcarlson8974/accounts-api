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