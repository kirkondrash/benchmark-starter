package com.deutsche.benchmarkstarter.dao;

import com.deutsche.benchmarkstarter.model.MethodMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "metrics")
public interface MethodMetricRepo extends JpaRepository<MethodMetric, Long> {
}
