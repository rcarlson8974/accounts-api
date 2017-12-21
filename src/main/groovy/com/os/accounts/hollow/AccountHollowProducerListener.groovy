package com.os.accounts.hollow


import com.netflix.hollow.api.producer.HollowProducerListener
import groovy.util.logging.Slf4j

import java.util.concurrent.TimeUnit

@Slf4j
class AccountHollowProducerListener implements HollowProducerListener {

    @Override
    void onProducerInit(long elapsed, TimeUnit unit) {

    }

    @Override
    void onProducerRestoreStart(long restoreVersion) {

    }

    @Override
    void onProducerRestoreComplete(HollowProducerListener.RestoreStatus status, long elapsed, TimeUnit unit) {

    }

    @Override
    void onNewDeltaChain(long version) {

    }

    @Override
    void onCycleStart(long version) {
        log.info("Starting cycle of version $version")
    }

    @Override
    void onCycleComplete(HollowProducerListener.ProducerStatus status, long elapsed, TimeUnit unit) {
        log.info("Finished cycle of version ${status.version} | ${status.status}")
    }

    @Override
    void onNoDeltaAvailable(long version) {
        log.info("NO DELTA WAS AVAILABLE | check data? | version $version")
    }

    @Override
    void onPopulateStart(long version) {

    }

    @Override
    void onPopulateComplete(HollowProducerListener.ProducerStatus status, long elapsed, TimeUnit unit) {

    }

    @Override
    void onPublishStart(long version) {
        log.info("Starting publishing of version $version")
    }

    @Override
    void onPublishComplete(HollowProducerListener.ProducerStatus status, long elapsed, TimeUnit unit) {
        log.info("Finished publishing of version ${status.version} | ${status.status}")
    }

    @Override
    void onArtifactPublish(HollowProducerListener.PublishStatus publishStatus, long elapsed, TimeUnit unit) {
    }

    @Override
    void onIntegrityCheckStart(long version) {

    }

    @Override
    void onIntegrityCheckComplete(HollowProducerListener.ProducerStatus status, long elapsed, TimeUnit unit) {

    }

    @Override
    void onValidationStart(long version) {

    }

    @Override
    void onValidationComplete(HollowProducerListener.ProducerStatus status, long elapsed, TimeUnit unit) {

    }

    @Override
    void onAnnouncementStart(long version) {
        log.info("Starting announcement of version $version")
    }

    @Override
    void onAnnouncementComplete(HollowProducerListener.ProducerStatus status, long elapsed, TimeUnit unit) {
        log.info("Finished announcement of version ${status.version} - Result: ${status.status.name()}")
    }
}
