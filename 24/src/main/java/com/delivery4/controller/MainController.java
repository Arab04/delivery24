package com.delivery4.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.delivery4.Reprository.UserReprository;
import com.delivery4.menus.Menus;
import com.delivery4.message.Messages;
import com.delivery4.modul.User;

@Component
public class MainController {
	
	@Autowired
	private Responses response;
	
	public void user(Long id,AbsSender send, Message message) throws TelegramApiException {
		switch(Messages.state.get(id)) {
			
		case SENDBUSINESS_NAME:
			response.sendBusinessName(id, send);
			break;
			
		case SENDCONTACT:
			response.sendContact(id, send, message);
			break;
			
		case SENDLOCATION:
			response.sendLocation(id, send, message);
			break;
			
		case FINISH_REGISTRATION:
			response.finishRegistration(id, send, message);
			break;
			
		case CUSTOMER_NUMBER:
			response.customerNumber(id, send, message);
			break;
			
		case CUSTOMER_ORDER:
			response.customerOrder(id, send, message);
			break;
			
		case FINISH_ORDER:
			response.finishOrder(id, send);
			break;
			
		case RE_ORDER:
			response.reOrder(id, send, message);
			break;
			
		case MAINMENU:
			response.mainMenu(id, message, send);
			break;
			
		}
	}
}
