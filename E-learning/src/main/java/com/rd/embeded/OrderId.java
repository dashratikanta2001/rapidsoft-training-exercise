package com.rd.embeded;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class OrderId implements  Serializable{

	
	private String username;
	
	private LocalDateTime orderDate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderId(String username, LocalDateTime orderDate) {
		super();
		this.username = username;
		this.orderDate = orderDate;
	}

	public OrderId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
