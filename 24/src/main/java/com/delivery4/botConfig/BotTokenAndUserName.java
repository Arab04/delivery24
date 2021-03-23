package com.delivery4.botConfig;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.delivery4.controller.State;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "telegrambot")
@Getter
@Setter
public class BotTokenAndUserName {

	private String botUserName;
	private String botToken;
	
	
	@Bean
	public HashMap<Long,State> state() {
		return new HashMap<Long,State>();
	}

}
