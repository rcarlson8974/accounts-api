package com.os.accounts.hollow

import spock.lang.Specification

class HollowHealthCheckSpec extends Specification {
  
  def 'checks if its ready'() {
    expect:
    new HollowHealthCheckTest().ready
  }

  static class HollowHealthCheckTest extends HollowHealthCheck {}
}
