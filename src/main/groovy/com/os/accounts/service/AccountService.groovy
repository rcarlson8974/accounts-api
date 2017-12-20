package com.os.accounts.service

import com.os.accounts.domain.Account
import com.os.accounts.hollow.AccountHollowConsumer
import com.os.accounts.transformer.AccountTransformer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountService {

//  @Autowired
//  AccountHollowConsumer hollowConsumer

  List<Account> getAccounts() {
//    if (hollowConsumer.indexReady) {
//      List<com.os.accounts.domain.generated.Account> accounts = hollowConsumer.getAccounts()
//      return accounts ? AccountTransformer.transformFromHollowToDomains(accounts) : null
//    }
    return null
  }
//
  Account getAccount(String accountId) {
//    if (hollowConsumer.indexReady) {
//      com.os.accounts.domain.generated.Account account = hollowConsumer.findMatch(accountId)
//      return account ? AccountTransformer.transformFromHollowToDomain(account) : null
//    }
    return null
  }
}
