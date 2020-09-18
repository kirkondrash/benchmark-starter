package com.deutsche.benchmarkcommon.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MethodMetric {
    @Id
    @GeneratedValue
    private Long id;

    private String methodName;
    private boolean successful;
    private long duration;
    private String exceptionName;
    private String args;

}
