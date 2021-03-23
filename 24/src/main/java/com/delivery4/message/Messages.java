package com.delivery4.message;

import java.util.HashMap;
import java.util.Map;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.delivery4.controller.State;

public class Messages {

	public static Map<Long,State> state = new HashMap<Long,State>();
	
	public static SendMessage businessName(Long id,String text) {
		SendMessage message = new SendMessage();
		message.setChatId(id);
		message.setText(text);
		return message;
	}
	
	public static SendMessage mainMenu(Long id,String text) {
		SendMessage message = new SendMessage();
		message.setChatId(id);
		message.setText(text);
		return message;
	}
	
	
}
