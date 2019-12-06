package com.faisal.bot.yoeker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class YoekerApplication {

	@Autowired
	static YoekerBot yoekerBot;

	public static void main(String[] args) {
		// Initialize Api Context
		ApiContextInitializer.init();

		// Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi();

		// Register our bot
		try {
			botsApi.registerBot(yoekerBot);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

		SpringApplication.run(YoekerApplication.class, args);
	}

}
