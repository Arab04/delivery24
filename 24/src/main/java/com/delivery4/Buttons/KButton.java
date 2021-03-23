package com.delivery4.Buttons;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class KButton {
	
	private Long id;
	private String text;
	
	private KeyboardRow row = null;
	private List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
	
	private KButton() {}
	
	public static KButton create() {
		KButton k = new KButton();
		return k;
	}
	
	public static KButton create(Long id) {
		KButton k = new KButton();
		k.setChatId(id);
		return k;
	}
	
	public KButton setChatId(Long id) {
		this.id = id;
		return this;
	}
	
	public KButton setText(String text) {
		this.text = text;
		return this;
	}
	
	public KButton row() {
		this.row = new KeyboardRow();
		return this;
	}
	
	public KButton button(String text) {
		this.row.add(text);
		return this;
	}
	
	public KButton Kbutton(KeyboardButton b) {
		this.row.add(b);
		return this;
	}
	
	public KButton endRow() {
		this.keyboard.add(this.row);
		this.row = null;
		return this;
	}
	
	public KButton clear() {
		this.keyboard.clear();
		return this;
	}
	
	public SendMessage build() {
		
		SendMessage m = new SendMessage();
		m.setChatId(id);
		m.setText(text);
		
		
		ReplyKeyboardMarkup mark = new ReplyKeyboardMarkup();
		
		mark.setOneTimeKeyboard(false);
		mark.setResizeKeyboard(true);
		mark.setSelective(true);
		mark.setKeyboard(keyboard);
		
		m.setReplyMarkup(mark);
		
		return m;
		
	}

}
