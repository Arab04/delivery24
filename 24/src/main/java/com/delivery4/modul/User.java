package com.delivery4.modul;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User extends AuditModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "business_name")
	private String businessName;
	
	@Column(name = "location_file")
	@Lob
	private Location location;
	
	@Column(name = "location_string")
	private String address;
	
	@Column(name = "contact_file")
	@Lob
	private Contact contact;
	
	@Column(name = "contact_string")
	private String number;
	
	@Column(name = "user_chat_id")
	private Long user_id;
	
//	@OneToMany(cascade =CascadeType.ALL,
//			fetch = FetchType.LAZY,
//			mappedBy = "user")
//	private List<Order> orders;
}
