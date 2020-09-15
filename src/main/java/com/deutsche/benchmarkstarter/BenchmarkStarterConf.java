package com.deutsche.benchmarkstarter;

import com.deutsche.benchmarkstarter.annotationhandlers.JavaBenchmarkHandlerAspect;
import com.deutsche.benchmarkstarter.annotationhandlers.RestBenchmarkHandlerAspect;
import com.deutsche.benchmarkstarter.dao.MethodMetricRepo;
import com.deutsche.benchmarkstarter.dao.RestMetricRepo;
import com.deutsche.benchmarkstarter.service.MethodMetricService;
import com.deutsche.benchmarkstarter.service.RestMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigurationPackage
//@EnableJpaRepositories
//@EntityScan
public class BenchmarkStarterConf {
    @Autowired
    MethodMetricRepo methodMetricRepo;

    @Autowired
    RestMetricRepo restMetricRepo;

    @Bean
    public JavaBenchmarkHandlerAspect aspectJava(){
        return new JavaBenchmarkHandlerAspect(new MethodMetricService(methodMetricRepo));
    }

    @Bean
    public RestBenchmarkHandlerAspect aspectRest(){
        return new RestBenchmarkHandlerAspect(new RestMetricService(restMetricRepo));
    }


}
