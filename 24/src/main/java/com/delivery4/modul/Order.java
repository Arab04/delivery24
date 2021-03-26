package com.delivery4.modul;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends AuditModel {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "customer_number")
	private String customerNumber;
	
	@Column(name = "customer_order")
	private String customerOrder;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private User user;

}
