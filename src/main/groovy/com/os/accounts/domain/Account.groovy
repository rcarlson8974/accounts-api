package com.os.accounts.domain

import com.netflix.hollow.core.write.objectmapper.HollowPrimaryKey
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
@HollowPrimaryKey(fields = ["accountId"])
class Account {
  String accountId
  String accountDesc
  String userId
  String passwordHint
  String pinIdHint
  String url
}
