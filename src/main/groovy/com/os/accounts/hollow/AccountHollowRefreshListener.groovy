package com.os.accounts.hollow

import com.netflix.hollow.api.consumer.HollowConsumer
import com.netflix.hollow.api.custom.HollowAPI
import com.netflix.hollow.core.read.engine.HollowReadStateEngine
import io.prometheus.client.Gauge

class AccountHollowRefreshListener implements HollowConsumer.RefreshListener {
  private static Gauge hollowRefreshGauge = Gauge
      .build()
      .name("os_hollow_refresh_metrics")
      .labelNames("metric")
      .help("Hollow Refresh Metrics")
      .register()

  @Override
  void snapshotUpdateOccurred(HollowAPI api, HollowReadStateEngine stateEngine, long version) throws Exception {
    hollowRefreshGauge.labels('snapshotUpdateOccurred').set(version)
  }

  @Override
  void deltaUpdateOccurred(HollowAPI api, HollowReadStateEngine stateEngine, long version) throws Exception {
    hollowRefreshGauge.labels('deltaUpdateOccurred').set(version)
  }

  @Override
  void refreshSuccessful(long beforeVersion, long afterVersion, long requestedVersion) {
    hollowRefreshGauge.labels('refreshSuccessful').set(requestedVersion)
  }

  @Override
  void blobLoaded(HollowConsumer.Blob transition) {}

  @Override
  void refreshStarted(long currentVersion, long requestedVersion) {
    hollowRefreshGauge.labels('refreshStarted').set(requestedVersion)
  }

  @Override
  void refreshFailed(long beforeVersion, long afterVersion, long requestedVersion, Throwable failureCause) {
    hollowRefreshGauge.labels('refreshFailed').set(requestedVersion)
  }

}
