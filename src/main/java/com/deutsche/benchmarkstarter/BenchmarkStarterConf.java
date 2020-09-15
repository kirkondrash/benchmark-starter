package com.deutsche.benchmarkstarter;

import com.deutsche.benchmarkstarter.annotationhandlers.BenchmarkHandlerAspect;
import com.deutsche.benchmarkstarter.dao.MethodMetricRepo;
import com.deutsche.benchmarkstarter.service.MethodMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@AutoConfigurationPackage
//@EnableJpaRepositories
//@EntityScan
public class BenchmarkStarterConf {
    @Autowired
    MethodMetricRepo methodMetricRepo;

    @Bean
    public BenchmarkHandlerAspect aspect(){
        return new BenchmarkHandlerAspect(new MethodMetricService(methodMetricRepo));
    }


}
