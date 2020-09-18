package com.deutsche.benchmarkalarmer;

import com.deutsche.benchmarkalarmer.mesagehandlers.MessageHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EntityScan("com.deutsche.benchmarkcommon.model")
@Slf4j
public class BenchmarkAlarmerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenchmarkAlarmerApplication.class, args);
    }

    @Bean
    @Autowired
    public TelegramBot telegramBot(@Value("${teletoken}") String telegramToken, List<MessageHandler> messageHandlerList){
        TelegramBot telegramBot = new TelegramBot(telegramToken);
        telegramBot.setUpdatesListener(updates -> {
            for (Update update: updates){
                Message message = update.message();
                if (message==null || message.text()==null) {
                    log.error("Caught a null message - edited and non-text messages are not supported!");
                    continue;
                }
                MessageHandler messageHandler = messageHandlerList
                        .stream()
                        .filter(handler -> handler.canHandle(message))
                        .findFirst()
                        .orElse((bot,msg) -> bot.execute(new SendMessage(msg.chat().id(),"Could not process your message!")));
                messageHandler.handle(telegramBot, message);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
        return telegramBot;
    }

}
