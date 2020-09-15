package com.deutsche.benchmarkstarter.model;

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
public class RestMetric {
    @Id
    @GeneratedValue
    private Long id;

    private String endpointName;
    private boolean successful;
    private int code;
    private long duration;
    private String exceptionName;
    private String args;

}
