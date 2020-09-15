package com.deutsche.benchmarkstarter.dao;

import com.deutsche.benchmarkstarter.model.MethodMetric;
import com.deutsche.benchmarkstarter.model.RestMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "rest_metrics_repo")
public interface RestMetricRepo extends JpaRepository<RestMetric, Long> {
}
