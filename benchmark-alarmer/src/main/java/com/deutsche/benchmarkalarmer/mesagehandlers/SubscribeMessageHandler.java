package com.deutsche.benchmarkalarmer.mesagehandlers;

import com.deutsche.benchmarkalarmer.service.SubscriberService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SubscribeMessageHandler implements MessageHandler {

    private final Pattern subscribePattern = Pattern.compile("^/subscribe ([\\w-]+)$");

    private final SubscriberService subscriberService;

    @Autowired
    public SubscribeMessageHandler(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }


    @Override
    public boolean canHandle(Message message) {
        return subscribePattern.matcher(message.text()).find();
    }

    @Override
    @SneakyThrows
    public void handle(TelegramBot telegramBot, Message message) {
        Matcher m = subscribePattern.matcher(message.text());
        m.find();
        String appName = m.group(1);
        subscriberService.addSubscriber(appName,message.chat().id());
    }
}
