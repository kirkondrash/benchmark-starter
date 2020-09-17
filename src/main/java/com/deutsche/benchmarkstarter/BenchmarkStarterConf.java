package com.deutsche.benchmarkstarter;

import com.deutsche.benchmarkstarter.annotationhandlers.JavaBenchmarkHandlerAspect;
import com.deutsche.benchmarkstarter.annotationhandlers.RestBenchmarkHandlerAspect;
import com.deutsche.benchmarkstarter.dao.MethodMetricRepo;
import com.deutsche.benchmarkstarter.dao.RestMetricRepo;
import com.deutsche.benchmarkstarter.service.AlarmerService;
import com.deutsche.benchmarkstarter.service.MethodMetricService;
import com.deutsche.benchmarkstarter.service.RestMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigurationPackage
//@EnableJpaRepositories
//@EntityScan
@ComponentScan
public class BenchmarkStarterConf {

    @Bean
    @Autowired
    public JavaBenchmarkHandlerAspect aspectJava(MethodMetricRepo methodMetricRepo){
        return new JavaBenchmarkHandlerAspect(new MethodMetricService(methodMetricRepo));
    }

    @Bean
    @Autowired
    public RestBenchmarkHandlerAspect aspectRest(RestMetricRepo restMetricRepo, AlarmerService alarmerService){
        return new RestBenchmarkHandlerAspect(new RestMetricService(restMetricRepo), alarmerService);
    }


}
