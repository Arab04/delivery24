package com.delivery4.modul;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.telegram.telegrambots.meta.api.objects.Location;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_info")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String userNumber;
	
	private String businessName;
	
	private Location location;
	
	private String address;
	
	private String userName;
	
	private Long user_id;
}
