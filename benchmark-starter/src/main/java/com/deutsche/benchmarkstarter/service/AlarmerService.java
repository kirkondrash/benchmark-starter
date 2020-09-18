package com.deutsche.benchmarkstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlarmerService {

    private final RestTemplate restTemplate;
    @Value("${spring.application.name}")
    private String appName;
    @Value("${alarmer.url}")
    private String alarmerUrl;

    @Autowired
    public AlarmerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendAlarm(String message){
        restTemplate.postForObject(alarmerUrl+"/alarm/"+appName,message,Void.TYPE);
    }
}
