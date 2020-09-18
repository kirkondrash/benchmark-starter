package com.deutsche.benchmarkalarmer.controller;

import com.deutsche.benchmarkalarmer.service.AlarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/alarm")
public class AlarmController {

    private final AlarmerService alarmerService;

    @Autowired
    public AlarmController(AlarmerService alarmerService) {
        this.alarmerService = alarmerService;
    }

    @PostMapping("/{appName}")
    public void sendAlarm(@PathVariable("appName") String appName, @RequestBody String message) {
        alarmerService.sendAlarm(appName, message);
    }

}
