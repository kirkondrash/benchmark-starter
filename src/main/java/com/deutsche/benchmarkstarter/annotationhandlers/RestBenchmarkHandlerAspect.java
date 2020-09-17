package com.deutsche.benchmarkstarter.annotationhandlers;


import com.deutsche.benchmarkstarter.annotations.AlarmTreshold;
import com.deutsche.benchmarkstarter.model.RestMetric;
import com.deutsche.benchmarkstarter.service.AlarmerService;
import com.deutsche.benchmarkstarter.service.RestMetricService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
public class RestBenchmarkHandlerAspect {

    private final RestMetricService restMetricService;
    private final AlarmerService alarmerService;

    @Autowired
    public RestBenchmarkHandlerAspect(RestMetricService restMetricService, AlarmerService alarmerService) {
        this.restMetricService = restMetricService;
        this.alarmerService = alarmerService;
    }

    @Pointcut("@within(com.deutsche.benchmarkstarter.annotations.RestBenchmark)")
    public void benchmarkedControllers(){}

    @Around("benchmarkedControllers()")
    public Object benchMarkMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestEndpoint = new StringBuilder()
                .append(request.getMethod()).append(" ")
                .append(request.getRequestURI()).append(" ")
                .append(Optional.ofNullable(request.getQueryString()).orElse(""))
                .toString();
        RestMetric.RestMetricBuilder restMetricBuilder = RestMetric
                .builder()
                .endpointName(requestEndpoint);
        long startTime = System.currentTimeMillis();
        long timeTaken = 0;
        try {
            Object result = proceedingJoinPoint.proceed();
            timeTaken = System.currentTimeMillis() - startTime;
            restMetricBuilder.successful(true);
            return result;
        } catch (Throwable ex) {
            timeTaken = System.currentTimeMillis() - startTime;
            String concatenatedStringArgs = Stream.of(proceedingJoinPoint.getArgs())
                    .map(Object::toString)
                    .collect(Collectors.joining("; "));
            restMetricBuilder
                    .exceptionName(ex.toString())
                    .args(concatenatedStringArgs)
                    .successful(false);
            throw ex;
        } finally {
            HttpServletResponse response = attributes.getResponse();
            restMetricBuilder
                    .duration(timeTaken)
                    .code(response.getStatus());
            restMetricService.saveRestMetric(restMetricBuilder.build());

            MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = signature.getMethod();

            AlarmTreshold alarmTreshold = method.getAnnotation(AlarmTreshold.class);
            if (alarmTreshold != null && timeTaken > alarmTreshold.value()){
                alarmerService.sendAlarm(String.format("Uh oh! %s just now took %d ns which is too much!", requestEndpoint, timeTaken));
            }
        }
    }

}
