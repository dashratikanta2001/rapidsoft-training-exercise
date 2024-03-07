package com.auth.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "otp")
public class Otp {


	private Long id;
	private String otp;
	private String email;
	private Date createdOn;
	private Long validTime;
	private Boolean isVerified;
	private Boolean isActive;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "created_on")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "valid_time_duration")
	public Long getValidTime() {
		return validTime;
	}

	public void setValidTime(Long validTime) {
		this.validTime = validTime;
	}

	@Column(name = "isVerified", columnDefinition = "boolean default false")
	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	@Column(name = "isActive", columnDefinition = "boolean default false")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Otp(Long id, String otp, String email, Date createdOn, Long validTime, Boolean isVerified,
			Boolean isActive) {
		super();
		this.id = id;
		this.otp = otp;
		this.email = email;
		this.createdOn = createdOn;
		this.validTime = validTime;
		this.isVerified = isVerified;
		this.isActive = isActive;
	}

	public Otp() {
		super();
		// TODO Auto-generated constructor stub
	}

}
