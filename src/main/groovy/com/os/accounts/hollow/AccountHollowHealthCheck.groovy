package com.os.accounts.hollow

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

@Component
class AccountHollowHealthCheck implements HealthIndicator {

//  @Autowired
//  AccountHollowConsumer hollowConsumer
//
//  @Override
//  Health health() {
//    return hollowConsumer.indexReady ? Health.up().build() : Health.down().build()
//  }

  @Override
  Health health() {
    return null
  }
}