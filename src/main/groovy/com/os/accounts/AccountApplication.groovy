package com.os.accounts

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@Slf4j
@CompileStatic
@SpringBootApplication
@ComponentScan(['com.os.accounts.*',])
class AccountApplication {
  static void main(final String[] args) {
    SpringApplication.run(this, args)
  }
}
