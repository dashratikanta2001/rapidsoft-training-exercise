package com.auth.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupDto {

	private String name;
	private String mobileNo;
	private String email;
	private String password;
	private String otp;

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
	@Size(min = 8, max = 16)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotBlank(message = "Otp can't be blank")
	@Size(min = 6, max = 6, message = "OTP must be 6 digit")
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public SignupDto(String name, String mobileNo, String email, String password, String otp) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.email = email;
		this.password = password;
		this.otp = otp;
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
