package com.deutsche.benchmarkstarter.service;

import com.deutsche.benchmarkcommon.model.RestMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestMetricService {

    private final RestTemplate restTemplate;
    @Value("${alarmer.url}")
    private String alarmerUrl;

    @Autowired
    public RestMetricService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void saveRestMetric(RestMetric restMetric){
        restTemplate.postForObject(alarmerUrl+"/rest_metrics_repo",restMetric,Void.TYPE);
    }
}
