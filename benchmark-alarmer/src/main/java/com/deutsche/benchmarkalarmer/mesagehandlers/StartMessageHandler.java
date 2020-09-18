package com.deutsche.benchmarkalarmer.mesagehandlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class StartMessageHandler implements MessageHandler {

    private final Pattern startPattern = Pattern.compile("^(/start|/help)$");
    private final String welcomeMessage = "Welcome to the Alarmer! Type \"/subscribe app-name\" to receive alarms about SLA violations for this app.";

    @Override
    public boolean canHandle(Message message) {
        return startPattern.matcher(message.text()).find();
    }

    @Override
    public void handle(TelegramBot telegramBot, Message message) {
        telegramBot.execute(new SendMessage(message.chat().id(), welcomeMessage));
    }
}
