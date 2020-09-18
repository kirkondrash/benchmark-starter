package com.deutsche.benchmarkalarmer.dao;

import com.deutsche.benchmarkcommon.model.RestMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "rest_metrics_repo")
public interface RestMetricRepo extends JpaRepository<RestMetric, Long> {
}
