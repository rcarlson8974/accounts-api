package com.os.accounts.transformer

import com.os.accounts.domain.Account

class AccountTransformer {

  static List<Account> transformFromHollowToDomains(def accounts) {
    List<Account> details = []
    accounts.each {
      details << transformFromHollowToDomain(it)
    }
    return details
  }

  static Account transformFromHollowToDomain(com.os.accounts.domain.generated.Account account) {
    return new Account(
        accountId: account.accountId.value,
        accountDesc: account.accountDesc.value,
        userId: account.userId.value,
        passwordHint: account.passwordHint.value,
        pinIdHint: account.pinIdHint.value,
        url: account.url.value)
  }
}