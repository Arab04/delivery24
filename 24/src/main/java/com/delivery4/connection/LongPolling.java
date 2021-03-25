package com.delivery4.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.delivery4.botConfig.BotTokenAndUserName;
import com.delivery4.controller.MainController;
import com.delivery4.controller.State;
import com.delivery4.menus.Menus;
import com.delivery4.message.Messages;

@Component
public class LongPolling extends TelegramLongPollingBot{
	
	@Autowired
	private BotTokenAndUserName token;
	
	@Autowired
	private MainController controller;
	
	@Override
	public void onUpdateReceived(Update update) {
		if(update.hasMessage()) 
			    if(Messages.state.containsKey(update.getMessage().getChatId())) {
			    	user(update.getMessage().getChatId(), update.getMessage());
			}
			
			else {
				SendMessage m = new SendMessage();
						m.setChatId(update.getMessage().getChatId());
						m.setText("Hi Send Your Name");
				send(m);
				Messages.state.put(update.getMessage().getChatId(), State.SENDBUSINESS_NAME);
			}
				
		}
	
	public void send(SendMessage message) {
		try {
			execute(message);
		}catch(TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	public void user(Long id,Message message) {
		try {
			controller.user(id, this,message);
		}catch(TelegramApiException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public String getBotUsername() {
		return token.getBotUserName();
	}

	@Override
	public String getBotToken() {
		return token.getBotToken();
	}

}
