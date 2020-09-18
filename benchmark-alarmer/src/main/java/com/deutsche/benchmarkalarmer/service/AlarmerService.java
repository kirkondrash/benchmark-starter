package com.deutsche.benchmarkalarmer.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmerService {

    private final TelegramBot telegramBot;
    private final SubscriberService subscriberService;

    @Autowired
    public AlarmerService(TelegramBot telegramBot, SubscriberService subscriberService){
        this.telegramBot = telegramBot;
        this.subscriberService = subscriberService;
    }

    public void sendAlarm(String appName, String message){
        String alarmMessage = String.format("[%s] %s", appName,message);
        for (Long id: subscriberService.getSubscribers(appName)){
            telegramBot.execute(new SendMessage(id, alarmMessage));
        }
    }
}
