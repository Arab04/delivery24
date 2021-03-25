package com.delivery4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.delivery4.Reprository.UserReprository;
import com.delivery4.menus.Menus;
import com.delivery4.message.Messages;
import com.delivery4.modul.User;

@Component
public class Responses {

	@Autowired
	private User user;
	
	@Autowired
	private UserReprository repo;
	
	public void sendBusinessName(Long id,AbsSender send) throws TelegramApiException {
		send.execute(Messages.message(id, "Send Business Name"));
		Messages.state.put(id, State.SENDCONTACT);
	}
	
	public void sendContact(Long id,AbsSender send, Message message) throws TelegramApiException {
		user.setUser_id(message.getChatId());
		user.setBusinessName(message.getText());
		user.setUserName(message.getChat().getUserName());
		send.execute(Menus.requestContact("Send your phone number", "Contact", id));
		Messages.state.put(id, State.SENDLOCATION);
	}
	
	public void sendLocation(Long id,AbsSender send, Message message) throws TelegramApiException {
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
	}
	
	public void finishRegistration(Long id,AbsSender send, Message message) throws TelegramApiException {
		if(message.hasLocation()) {
			user.setLocation(message.getLocation());
			repo.save(user);
			send.execute(Menus.mainMenu(id, "Thank you for registration", "Order", "Daily Orders", "Settings"));
			Messages.state.put(id, State.MAINMENU);
		}
		else {
			user.setAddress(message.getText());
			repo.save(user);
		send.execute(Menus.mainMenu(id, "Thank you for registration", "Order", "Daily Orders", "Settings"));
		Messages.state.put(id, State.MAINMENU);
		}
	}
	
	
	public void customerNumber(Long id,AbsSender send, Message message) throws TelegramApiException {
		if(message.getText().equals("Cancel")) {
			send.execute(Menus.mainMenu(id, "Order was cancelled", "Order", "Daily Orders", "Settings"));
			Messages.state.put(id, State.MAINMENU);
		}
		else {
		send.execute(Messages.message(id, "Please send Cutomer Number"));
		Messages.state.put(id, State.CUSTOMER_ORDER);
		
		}
	}
	
	public void customerOrder(Long id,AbsSender send, Message message) throws TelegramApiException {
		if(message.getText().equals("Cancel")) {
			send.execute(Menus.mainMenu(id, "Order was cancelled", "Order", "Daily Orders", "Settings"));
			Messages.state.put(id, State.MAINMENU);
		}
		else {
		    send.execute(Messages.message(id, "Please send customer order"));
		    Messages.state.put(id, State.FINISH_ORDER);
		}
	}
	
	
	public void finishOrder(Long id, AbsSender send) throws TelegramApiException {
		send.execute(Menus.finishOrder(id, "Our courier will be online within 3 minuts", "Order again","Finish order"));
		Messages.state.put(id, State.RE_ORDER);
	}
	
	public void reOrder(Long id,AbsSender send, Message message) throws TelegramApiException {
		if(message.getText().equals("Order again")) {
			send.execute(Menus.cancelOrder(id, "Please send Cutomer Number", "Cancel"));
			Messages.state.put(id, State.CUSTOMER_NUMBER);
		}
		else if(message.getText().equals("Finish order")) {
			send.execute(Menus.mainMenu(id, "Order finished", "Order", "Daily Orders", "Settings"));
			Messages.state.put(id, State.MAINMENU);
		}
		
		else {
			send.execute(Messages.message(id, "Please use buttons"));
			Messages.state.put(id, State.RE_ORDER);
		}
	}
	
	
	public void mainMenu(Long id,Message message,AbsSender send) throws TelegramApiException {
		if(message.getText().equals("Order")) {
			send.execute(Menus.cancelOrder(id, "use cancel button to cancel order", "Cancel"));
			Messages.state.put(id, State.CUSTOMER_NUMBER);
		}
	}
	
	
	
	
	
	
	
}
