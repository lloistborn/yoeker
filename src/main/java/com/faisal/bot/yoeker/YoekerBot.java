package com.faisal.bot.yoeker;

import com.faisal.bot.yoeker.configuration.YoekerBotProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Mono;

@Component
public class YoekerBot extends TelegramLongPollingBot {

  private static final Logger LOGGER = LoggerFactory.getLogger(YoekerBot.class);

  private YoekerBotProperties botProperties;

  @Autowired
  public YoekerBot(YoekerBotProperties botProperties) {
    this.botProperties = botProperties;
  }

  @Override
  public void onUpdateReceived(Update update) {

    Mono.just(update)
        .filter(Update::hasMessage)
        .filter(upd -> upd.getMessage().hasText())
        .flatMap(upd -> {
          String messageText = upd.getMessage().getText();
          long chatId = upd.getMessage().getChatId();

          return Mono.fromCallable(() ->
              execute(new SendMessage().setChatId(chatId).setText(messageText)))
              .doOnNext(data -> LOGGER.info("Sent message \"{}\" to {}", messageText, chatId))
              .doOnError(throwable -> LOGGER.error("Failed to send message \"{}\" to {} due to error: {}",
                  messageText, chatId, throwable.getMessage()));
        })
        .subscribe();
  }

  @Override
  public String getBotUsername() {
    return botProperties.getUsername();
  }

  @Override
  public String getBotToken() {
    return botProperties.getToken();
  }
}
