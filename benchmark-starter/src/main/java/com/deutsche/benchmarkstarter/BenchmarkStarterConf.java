package com.deutsche.benchmarkstarter;

import com.deutsche.benchmarkstarter.annotationhandlers.JavaBenchmarkHandlerAspect;
import com.deutsche.benchmarkstarter.annotationhandlers.RestBenchmarkHandlerAspect;
import com.deutsche.benchmarkstarter.service.AlarmerService;
import com.deutsche.benchmarkstarter.service.MethodMetricService;
import com.deutsche.benchmarkstarter.service.RestMetricService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
@EnableConfigurationProperties(AlarmerConf.class)
public class BenchmarkStarterConf {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }

    @Bean
    public JavaBenchmarkHandlerAspect aspectJava(MethodMetricService methodMetricService){
        return new JavaBenchmarkHandlerAspect(methodMetricService);
    }

    @Bean
    public RestBenchmarkHandlerAspect aspectRest(RestMetricService restMetricService, AlarmerService alarmerService){
        return new RestBenchmarkHandlerAspect(restMetricService, alarmerService);
    }


}
