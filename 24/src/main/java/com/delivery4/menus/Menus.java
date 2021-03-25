
package com.delivery4.menus;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import com.delivery4.Buttons.KButton;


public class Menus {

	public static SendMessage mainMenu(Long id,String text, String order, String listOfOrders,String settings) {
		SendMessage mainMenu = KButton.create(id)
				.setText(text)
				.row()
				.button(order)
				.button(listOfOrders)
				.endRow()
				.row()
				.button(settings)
				.endRow()
				.build();
		return mainMenu;
	}
	
	public static SendMessage requestLocation(String text,String button,Long id) {
		SendMessage location = KButton.create(id)
				.setText(text)
				.row()
				.Kbutton(new KeyboardButton().setRequestLocation(true).setText(button))
				.endRow()
				.build();
		return location;
	}
	
	public static SendMessage requestContact(String text,String button, Long id) {
		SendMessage a = KButton.create(id)
				.setText(text)
				.row()
				.Kbutton(new KeyboardButton().setRequestContact(true).setText(button))
				.endRow()
				.build();
		return a;
	}
	
	public static SendMessage cancelOrder(Long id,String text,String button) {
		SendMessage cancel = KButton.create(id)
				.setText(text)
				.row()
				.button(button)
				.endRow()
				.build();
		return cancel;
	}
	
	public static SendMessage finishOrder(Long id,String text,String orderAgain,String backToMainMenu) {
		SendMessage finish = KButton.create(id)
				.setText(text)
				.row()
				.button(orderAgain)
				.button(backToMainMenu)
				.endRow()
				.build();
		return finish;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
