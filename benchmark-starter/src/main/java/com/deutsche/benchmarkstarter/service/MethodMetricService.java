package com.deutsche.benchmarkstarter.service;

import com.deutsche.benchmarkcommon.model.MethodMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MethodMetricService {

    private final RestTemplate restTemplate;
    @Value("${alarmer.url}")
    private String alarmerUrl;

    @Autowired
    public MethodMetricService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void saveMethodMetric(MethodMetric methodMetric){
        restTemplate.postForObject(alarmerUrl+"/java_metrics_repo",methodMetric,Void.TYPE);
    }
}
