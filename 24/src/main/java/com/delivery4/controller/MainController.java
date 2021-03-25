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
	private User user;
	
	@Autowired
	private UserReprository repo;
	
	public void user(Long id,AbsSender send, Message message) throws TelegramApiException {
		switch(Messages.state.get(id)) {
		
		case REGISTRATION:
			Messages.state.put(id, State.SENDBUSINESS_NAME);
			registration(id, send, message);
			break;
			
		case ORDER:
			Messages.state.put(id, State.CUSTOMER_NUMBER);
			order(id, send, message);
			break;
			
		case MAINMENU:
			if(message.getText().equals("Order")) {
				send.execute(Menus.cancelOrder(id, "use cancel button to cancel order", "Cancel"));
				Messages.state.put(id, State.ORDER);
			}
			break;
		
		}
	}
	
	public void order(Long id, AbsSender send,Message message) throws TelegramApiException {
		switch(Messages.state.get(id)) {
		
		case CUSTOMER_NUMBER:
			if(message.getText().equals("Cancel")) {
				send.execute(Menus.mainMenu(id, "Order was cancelled", "Order", "Daily Orders", "Settings"));
				Messages.state.put(id, State.MAINMENU);
				user(id, send, message);
			}
			else {
			send.execute(Messages.message(id, "Please send Cutomer Number"));
			Messages.state.put(id, State.CUSTOMER_ORDER);
			
			}
			break;
			
		case CUSTOMER_ORDER:
			if(message.getText().equals("Cancel")) {
				send.execute(Menus.mainMenu(id, "Order was cancelled", "Order", "Daily Orders", "Settings"));
				Messages.state.put(id, State.MAINMENU);
				user(id, send, message);
			}
			else {
			    send.execute(Messages.message(id, "Please send customer order"));
			    Messages.state.put(id, State.FINISH_ORDER);
			}
			break;
			
		case FINISH_ORDER:
			send.execute(Menus.finishOrder(id, "Our courier will be online within 3 minuts", "Order again","Finish order"));
			Messages.state.put(id, State.RE_ORDER);
			break;
			
		case RE_ORDER:
			if(message.getText().equals("Order again")) {
				send.execute(Menus.cancelOrder(id, "Please send Cutomer Number", "Cancel"));
				Messages.state.put(id, State.CUSTOMER_NUMBER);
			}
			else if(message.getText().equals("Finish order")) {
				send.execute(Menus.mainMenu(id, "Order finished", "Order", "Daily Orders", "Settings"));
				Messages.state.put(id, State.MAINMENU);
				user(id, send, message);
			}
			
			else {
				send.execute(Messages.message(id, "Please use buttons"));
				Messages.state.put(id, State.RE_ORDER);
			}
			break;
		}
	}
	
	
	public void registration(Long id,AbsSender send,Message message) throws TelegramApiException {
		
		switch(Messages.state.get(id)) {
		case SENDBUSINESS_NAME:
			send.execute(Messages.message(id, "Send Business Name"));
			Messages.state.put(id, State.SENDCONTACT);
			break;
			
		case SENDCONTACT:
			user.setUser_id(message.getChatId());
			user.setBusinessName(message.getText());
			user.setUserName(message.getChat().getUserName());
			send.execute(Menus.requestContact("Send your phone number", "Contact", id));
			Messages.state.put(id, State.SENDLOCATION);
			break;
			
		case SENDLOCATION:
			if(message.hasContact()) {
				user.setContact(message.getContact());
				send.execute(Menus.requestLocation("Send location of your business", "Location", id));
				Messages.state.put(id, State.MAINMENU);
			}
			
			else {
			user.setNumber(message.getText());
			send.execute(Menus.requestLocation("Send location of your business", "Location", id));
			Messages.state.put(id, State.FINISH_REGISTRATION);
			}
			break;
			
		case FINISH_REGISTRATION:
			if(message.hasLocation()) {
				user.setLocation(message.getLocation());
				repo.save(user);
				send.execute(Menus.mainMenu(id, "Thank you for registration", "Order", "Daily Orders", "Settings"));
				Messages.state.put(id, State.MAINMENU);
				user(id, send, message);
			}
			else {
				user.setAddress(message.getText());
				repo.save(user);
			send.execute(Menus.mainMenu(id, "Thank you for registration", "Order", "Daily Orders", "Settings"));
			Messages.state.put(id, State.MAINMENU);
			user(id, send, message);
			}
			break;
		}
	}
}
