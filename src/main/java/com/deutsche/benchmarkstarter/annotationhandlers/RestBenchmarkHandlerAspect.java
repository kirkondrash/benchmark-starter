package com.deutsche.benchmarkstarter.annotationhandlers;


import com.deutsche.benchmarkstarter.model.MethodMetric;
import com.deutsche.benchmarkstarter.model.RestMetric;
import com.deutsche.benchmarkstarter.service.MethodMetricService;
import com.deutsche.benchmarkstarter.service.RestMetricService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
public class RestBenchmarkHandlerAspect {

    private final RestMetricService restMetricService;

    @Autowired
    public RestBenchmarkHandlerAspect(RestMetricService restMetricService) {
        this.restMetricService = restMetricService;
    }

    @Pointcut("@annotation(com.deutsche.benchmarkstarter.annotations.RestBenchmark)")
    public void benchmarkedMethods(){}

    @Pointcut("@within(com.deutsche.benchmarkstarter.annotations.RestBenchmark)")
    public void benchmarkedClasses(){}

    @Around("benchmarkedMethods() || benchmarkedClasses()")
    public Object benchMarkMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        RestMetric.RestMetricBuilder restMetricBuilder = RestMetric
                .builder()
                .endpointName(request.getMethod() + " " + request.getRequestURI());
        try {
            long startTime = System.currentTimeMillis();
            Object result = proceedingJoinPoint.proceed();
            long timeTaken = System.currentTimeMillis() - startTime;
            HttpServletResponse response = attributes.getResponse();
            restMetricBuilder
                    .duration(timeTaken)
                    .successful(true)
                    .code(response.getStatus());
            return result;
        } catch (Throwable ex) {
            HttpServletResponse response = attributes.getResponse();
            String concatenatedStringArgs = Stream.of(proceedingJoinPoint.getArgs())
                    .map(Object::toString)
                    .collect(Collectors.joining("; "));
            restMetricBuilder
                    .exceptionName(ex.toString())
                    .args(concatenatedStringArgs)
                    .successful(false);
            throw ex;
        } finally {
            restMetricService.saveRestMetric(restMetricBuilder.build());
        }
    }

}
