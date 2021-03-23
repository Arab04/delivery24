package com.delivery4.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.delivery4.menus.Menus;
import com.delivery4.message.Messages;

@Component
public class MainController {
	
	
	public void menuController(Long id,AbsSender send) {
		
	}
	
	public void registration(Long id,AbsSender send,Message message) throws TelegramApiException {
		
		switch(Messages.state.get(id)) {
		case SENDBUSINESS_NAME:
			send.execute(Messages.businessName(id, "Send Business Name"));
			Messages.state.put(id, State.SENDCONTACT);
			break;
			
		case SENDCONTACT:
			send.execute(Menus.requestContact("Send your phone number", "Contact", id));
			Messages.state.put(id, State.SENDLOCATION);
			break;
			
		case SENDLOCATION:
			if(message.hasContact()) {
				
			}
			send.execute(Menus.requestLocation("Send location of your business", "Location", id));
			Messages.state.put(id, State.MAINMENU);
			break;
			
		case MAINMENU:
			send.execute(Messages.mainMenu(id, "Thank you for registration"));
			send.execute(Menus.mainMenu(id, "Main Menu", "Order", "Daily Orders", "Settings"));
			Messages.state.put(id, State.COMMANDS);
			break;
			
		case COMMANDS:
			break;
		}
	}
}
