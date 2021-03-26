package com.delivery4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.delivery4.connection.LongPolling;

@SpringBootApplication
@EnableJpaAuditing
public class Application implements CommandLineRunner{
	
	@Autowired
	private static LongPolling longpolling;
	
	
	public Application(LongPolling longPolling) {
		this.longpolling = longPolling;
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		TelegramBotsApi api = new TelegramBotsApi();
		try {
			api.registerBot(longpolling);
		}
		catch(TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(Application.class, args);
	}

}
