package com.deutsche.benchmarkalarmer.service;

import com.deutsche.benchmarkalarmer.dao.MethodMetricRepo;
import com.deutsche.benchmarkcommon.model.MethodMetric;
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
