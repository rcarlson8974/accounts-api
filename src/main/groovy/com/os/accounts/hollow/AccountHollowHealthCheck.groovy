package com.os.accounts.hollow

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

@Component
class AccountHollowHealthCheck implements HealthIndicator {

  @Autowired
  AccountProducer hollowProducer

  @Autowired
  AccountConsumer hollowConsumer

  @Override
  Health health() {
    hollowProducer.init()
    return hollowProducer.isReady() && hollowConsumer.isReady() ? Health.up().build() : Health.down().build()
  }

}