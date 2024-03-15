package com.auth.dto;

import javax.validation.constraints.Size;

public class ChangePasswordDto {

	private String email;
	
	@Size(min = 8, max = 16)
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
