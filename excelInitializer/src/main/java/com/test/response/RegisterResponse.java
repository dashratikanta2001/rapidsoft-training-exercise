package com.test.response;

public class RegisterResponse {

	private String userName;

	private String email;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RegisterResponse(String userName, String email) {
		super();
		this.userName = userName;
		this.email = email;
	}

	public RegisterResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
