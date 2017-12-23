package com.os.accounts.hollow

import spock.lang.Specification

class AccountHollowConfigSpec extends Specification {

  def 'gets wired'() {
    when:
    AccountHollowConfig config = new AccountHollowConfig(bucket: 'b', domain: 'd', namespace: 'ns', applicationName: 'an', numStatesBetweenSnapshots: 5, numOfSnapshotsToKeep: 3)

    then:
    config.bucket == 'b'
    config.domain == 'd'
    config.namespace == 'ns'
    config.applicationName == 'an'
    config.numStatesBetweenSnapshots == 5
    config.numOfSnapshotsToKeep == 3

  }
}
