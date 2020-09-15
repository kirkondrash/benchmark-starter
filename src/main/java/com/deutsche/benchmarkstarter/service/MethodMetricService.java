package com.deutsche.benchmarkstarter.service;

import com.deutsche.benchmarkstarter.dao.MethodMetricRepo;
import com.deutsche.benchmarkstarter.model.MethodMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodMetricService {

    private final MethodMetricRepo methodMetricRepo;

    @Autowired
    public MethodMetricService(MethodMetricRepo methodMetricRepo) {
        this.methodMetricRepo = methodMetricRepo;
    }

    public void saveMethodMetric(MethodMetric methodMetric){
        methodMetricRepo.save(methodMetric);
    }
}
