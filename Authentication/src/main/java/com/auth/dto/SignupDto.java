package com.auth.dto;

import javax.validation.constraints.NotBlank;

public class SignupDto {

	private String name;
	private String mobileNo;
	private String email;
	private String password;

	@NotBlank(message = "Name can't be blank")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank(message = "Mobile number can't be blank")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@NotBlank(message = "Email can't be blank")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank(message = "Password can't be blank")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SignupDto(String name, String mobileNo, String email, String password) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.email = email;
		this.password = password;
	}

	public SignupDto() {
		super();
	}

	@Override
	public String toString() {
		return "SignupDto [name=" + name + ", mobileNo=" + mobileNo + ", email=" + email + ", password=" + password
				+ "]";
	}

}
