package com.deutsche.benchmarkstarter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AlarmerService {

    private final TelegramBot telegramBot;

    @Autowired
    public AlarmerService(@Value("${teletoken}") String telegramToken){
        this.telegramBot = new TelegramBot(telegramToken);
    }

    public void sendAlarm(String message){
        SendResponse response = telegramBot.execute(new SendMessage("239181438", message));
        System.out.println(response);
    }
}
