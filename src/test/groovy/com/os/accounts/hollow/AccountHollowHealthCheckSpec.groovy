package com.os.accounts.hollow

import com.os.accounts.BaseSpecification
import org.springframework.boot.actuate.health.Health
import spock.lang.Unroll

class AccountHollowHealthCheckSpec extends BaseSpecification {

  @Unroll
  def 'performs hollow health check - #description'() {

    setup:
    AccountConsumer mockConsumer = Mock()
    AccountProducer mockProducer = Mock()
    AccountHollowHealthCheck hollowHealthCheck = new AccountHollowHealthCheck(hollowConsumer: mockConsumer, hollowProducer: mockProducer)

    when:
    Health health = hollowHealthCheck.health()

    then:
    1 * mockProducer.isReady() >> producerState
    consumerCalls * mockConsumer.isReady() >> consumerState
    health == expectedResponse

    where:
    description        | producerState | consumerState | consumerCalls | expectedResponse
    'consumer is down' | true          | false         | 1             | Health.down().build()
    'consumer is up'   | true          | true          | 1             | Health.up().build()
    'producer is down' | false         | true          | 0             | Health.down().build()
    'producer is up'   | true          | true          | 1             | Health.up().build()
    'both down'        | false         | false         | 0             | Health.down().build()
  }
}
