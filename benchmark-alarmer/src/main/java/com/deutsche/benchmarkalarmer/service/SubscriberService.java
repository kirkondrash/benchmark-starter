package com.deutsche.benchmarkalarmer.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class SubscriberService {

    private final Map<String, Set<Long>> subscribers = new HashMap<>();

    public void addSubscriber(String appName, Long id){
        Set<Long> subscribersForApp = subscribers.computeIfAbsent(appName,s -> new HashSet<>());
        subscribersForApp.add(id);
    }

    public Set<Long> getSubscribers(String appName){
        return subscribers.getOrDefault(appName, new HashSet<>());
    }

}
