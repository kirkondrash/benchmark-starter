package com.deutsche.benchmarkstarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="alarmer")
public class AlarmerConf {

    private String url = "http://localhost:8080";
}
