package com.delivery4.Buttons;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class Inline {
	
	private String text;
	private Long id;
	private List<List<InlineKeyboardButton>> keyboard = new ArrayList<List<InlineKeyboardButton>>();
	private List<InlineKeyboardButton> row = null;
	
	private Inline() {}
	
	public Inline setText(String text) {
		this.text = text;
		return this;
	}
	
	public Inline setChatId(Long id) {
		this.id = id;
		return this;
	}
	
	public static Inline create(Long id) {
		Inline i = new Inline();
		i.setChatId(id);
		return i;
	} 
	
	public Inline row() {
		this.row = new ArrayList<InlineKeyboardButton>();
		return this;
	}
	
	public Inline button(String text,String callback) {
		this.row.add(new InlineKeyboardButton().setText(text).setCallbackData(callback));
		return this;
	}
	
	public Inline lbutton(String text, String link) {
		this.row.add(new InlineKeyboardButton().setText(text).setUrl(link));
		return this;
	}
	
	public Inline endRow() {
		this.keyboard.add(this.row);
		this.row = null;
		return this;
	}
	
	
	public SendMessage build() {
	
		SendMessage m = new SendMessage();
		m.setText(this.text);
		m.setChatId(this.id);
		
		InlineKeyboardMarkup mark = new InlineKeyboardMarkup();
		mark.setKeyboard(this.keyboard);
		m.setReplyMarkup(mark);
		
		return m;
	}

}
