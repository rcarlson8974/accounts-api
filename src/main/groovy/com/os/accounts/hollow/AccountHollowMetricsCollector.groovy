package com.os.accounts.hollow

import com.netflix.hollow.api.metrics.HollowConsumerMetrics
import com.netflix.hollow.api.metrics.HollowMetricsCollector
import io.prometheus.client.Gauge

class AccountHollowMetricsCollector extends HollowMetricsCollector<HollowConsumerMetrics> {

  private static Gauge hollowConsumerGauge = Gauge
      .build()
      .name("os_hollow_consumer_metrics")
      .labelNames("metric")
      .help("Hollow Consumer Metrics")
      .register()

  private static void set(String metric, Double value) {
    hollowConsumerGauge.labels(metric).set(value)
  }

  private static final long kb = 1024

  @Override
  void collect(HollowConsumerMetrics metrics) {
    //Common hollow metrics
    metrics.typeHeapFootprint.each { String type, Long heapCost ->
      set("${type}_heap_cost_kb", heapCost / kb)
    }
    metrics.typePopulatedOrdinals.each { String type, Integer ordinals ->
      set("${type}_domain_object_count", ordinals)
    }
    set("total_heap_cost_kb", metrics.totalHeapFootprint)
    set("total_domain_object_count", metrics.totalPopulatedOrdinals)
    set("current_version", metrics.currentVersion)

    //Consumer specific metrics
    set("refresh_failed", metrics.refreshFailed)
    set("refresh_succeeded", metrics.getRefreshSucceded())
  }
}
