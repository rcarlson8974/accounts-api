package com.os.accounts.hollow

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
//class AccountHollowConfig extends GCSHollowConfig {
class AccountHollowConfig {

  @Value('${hollow.bucket}')
  String bucket

  @Value('${hollow.namespace}')
  String namespace

  @Value('${hollow.domain}')
  String domain

  @Value('${hollow.applicationName}')
  String applicationName

  @Value('${hollow.numStatesBetweenSnapshots:30}')
  int numStatesBetweenSnapshots

  @Value('${hollow.numOfSnapshotsToKeep:15}')
  Integer numOfSnapshotsToKeep

}
