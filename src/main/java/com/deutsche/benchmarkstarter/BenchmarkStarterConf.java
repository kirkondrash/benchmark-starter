package com.deutsche.benchmarkstarter;

import com.deutsche.benchmarkstarter.annotationhandlers.BenchmarkHandlerAspect;
import com.deutsche.benchmarkstarter.dao.MethodMetricRepo;
import com.deutsche.benchmarkstarter.service.MethodMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigurationPackage
public class BenchmarkStarterConf {
    @Autowired
    MethodMetricRepo methodMetricRepo;

    @Bean
    public BenchmarkHandlerAspect aspect(){
        return new BenchmarkHandlerAspect(new MethodMetricService(methodMetricRepo));
    }


}
