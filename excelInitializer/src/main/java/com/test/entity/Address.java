package com.test.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String streetAddress;
	private String city;
	private String state;
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Address(String streetAddress, String city, String state) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
