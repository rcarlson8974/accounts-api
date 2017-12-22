package com.os.accounts.service

import com.os.accounts.domain.Account
import com.os.accounts.hollow.AccountConsumer
import com.os.accounts.hollow.AccountProducer
import com.os.accounts.transformer.AccountTransformer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountService {

  @Autowired
  AccountConsumer consumer

  @Autowired
  AccountProducer producer

  List<Account> getAccounts() {
    if (consumer.isReady()) {
      List<com.os.accounts.domain.generated.Account> accounts = consumer.getAccounts()
      return accounts ? AccountTransformer.transformFromHollowToDomains(accounts) : null
    }
    return null
  }

  Account getAccount(String accountId) {
    if (consumer.isReady()) {
      com.os.accounts.domain.generated.Account account = consumer.findMatch(accountId)
      return account ? AccountTransformer.transformFromHollowToDomain(account) : null
    }
    return null
  }

  Account createAccount(Account account) {
    producer.publishAccount(account)
    return account
  }


}
