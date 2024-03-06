package com.auth.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Entity
//@Table(name = "otp")
public class Otp {

	private Long id;
	private String otp;
	private Date validTime;
	private Boolean verified;


	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "otp")
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Column(name = "valid_time")
	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	@Column(name = "is_verified")
	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Otp(Long id, String otp, Date validTime) {
		super();
		this.id = id;
		this.otp = otp;
		this.validTime = validTime;
	}

	public Otp() {
		super();
		// TODO Auto-generated constructor stub
	}

}
