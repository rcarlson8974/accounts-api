package com.os.accounts.config

import com.netflix.hollow.api.consumer.HollowConsumer
import com.netflix.hollow.api.consumer.fs.HollowFilesystemAnnouncementWatcher
import com.netflix.hollow.api.consumer.fs.HollowFilesystemBlobRetriever
import com.os.accounts.hollow.AccountHollowConfig
import com.os.accounts.hollow.AccountHollowConsumer
import com.os.accounts.util.ObjectMapper
import groovy.transform.CompileStatic
import groovy.transform.ToString
import groovy.util.logging.Slf4j
import org.apache.catalina.startup.Tomcat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@ToString
@Configuration
@CompileStatic
@EnableScheduling
@EnableConfigurationProperties
@Slf4j
class AccountConfiguration extends WebMvcConfigurerAdapter {

//  public static final String SCRATCH_DIR = System.getProperty("java.io.tmpdir")
//
//  @Bean
//  ObjectMapper neptuneObjectMapper() {
//    return new ObjectMapper()
//  }
//
//  @Bean
//  TomcatEmbeddedServletContainerFactory tomcatFactory() {
//    return new TomcatEmbeddedServletContainerFactory() {
//
//      @Override
//      protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
//        tomcat.getEngine().setBackgroundProcessorDelay(1)
//        log.info("heap max: ${Runtime.getRuntime().maxMemory()} bytes")
//        log.info("user.timezone: ${System.getProperty('user.timezone')}")
//        return super.getTomcatEmbeddedServletContainer(tomcat)
//      }
//    }
//  }
//
//  @Bean
//  HollowConsumer.AnnouncementWatcher getAnnouncementWatcher() {
//    File publishDir = new File(SCRATCH_DIR, "publish-dir")
//    publishDir.mkdir()
//    return new HollowFilesystemAnnouncementWatcher(publishDir)
//  }
//
//  @Bean
//  HollowConsumer.BlobRetriever getBlobRetriever(@Autowired AccountHollowConfig hollowConfig) {
//    File publishDir = new File(SCRATCH_DIR, "publish-dir")
//    return new HollowFilesystemBlobRetriever(publishDir, null)
//  }
//
//  @Bean
//  AccountHollowConsumer getConsumer() {
//    return new AccountHollowConsumer()
//  }

}
