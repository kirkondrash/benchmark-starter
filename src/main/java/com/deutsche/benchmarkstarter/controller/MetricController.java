package com.deutsche.benchmarkstarter.controller;

import com.deutsche.benchmarkstarter.model.MethodMetric;
import com.deutsche.benchmarkstarter.model.RestMetric;
import com.deutsche.benchmarkstarter.service.MethodMetricService;
import com.deutsche.benchmarkstarter.service.RestMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/statistics")
public class MetricController {

    private final RestMetricService restMetricService;
    private final MethodMetricService methodMetricService;

    @Autowired
    public MetricController(RestMetricService restMetricService, MethodMetricService methodMetricService) {
        this.restMetricService = restMetricService;
        this.methodMetricService = methodMetricService;
    }

    @GetMapping
    public String getStatistics() {
        return "HELLO";
    }

}
