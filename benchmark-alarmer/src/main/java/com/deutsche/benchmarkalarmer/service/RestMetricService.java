package com.deutsche.benchmarkalarmer.service;

import com.deutsche.benchmarkalarmer.dao.RestMetricRepo;
import com.deutsche.benchmarkcommon.model.RestMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestMetricService {

    private final RestMetricRepo restMetricRepo;

    @Autowired
    public RestMetricService(RestMetricRepo restMetricRepo) {
        this.restMetricRepo = restMetricRepo;
    }

    public void saveRestMetric(RestMetric restMetric){
        restMetricRepo.save(restMetric);
    }
}
