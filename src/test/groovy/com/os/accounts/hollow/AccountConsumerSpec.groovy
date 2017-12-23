package com.os.accounts.hollow

import spock.lang.Specification

class AccountConsumerSpec extends Specification {

  def 'gets wired'()  {
    when:
    def hollowConsumer = new HollowConsumerTest()

    then:
    !hollowConsumer.isReady()
  }


  static class HollowConsumerTest extends AccountConsumer {

  }

}
