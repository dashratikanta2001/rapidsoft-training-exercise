package com.auth.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	private Long id;
	private String name;
	private Long mobileNo;
	private String email;
	private String password;
	private Date createdOn;
	private Date updatedOn;
//	private String profilePath;
	private boolean isActive;
//	private String otp;
//	private Long otpTimer;
//	private Boolean verified;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "mobile_no")
	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name = "email", updatable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "pasword", length = 70)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "created_on")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "updated_on")
	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

//	@Column(name = "profile_path")
//	public String getProfilePath() {
//		return profilePath;
//	}
//
//	public void setProfilePath(String profilePath) {
//		this.profilePath = profilePath;
//	}

	@Column(name = "isActive", columnDefinition = "boolean default false")
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

//	@Column(name = "otp")
//	public String getOtp() {
//		return otp;
//	}
//
//	public void setOtp(String otp) {
//		this.otp = otp;
//	}
//
//	@Column(name = "valid_time")
//	public Long getOtpTimer() {
//		return otpTimer;
//	}
//
//	public void setOtpTimer(Long otpTimer) {
//		this.otpTimer = otpTimer;
//	}
//
//	@Column(name = "is_verified")
//	public Boolean getVerified() {
//		return verified;
//	}
//
//	public void setVerified(Boolean verified) {
//		this.verified = verified;
//	}

	public User(Long id, String name, Long mobileNo, String email, String password, Date createdOn, Date updatedOn,
			Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.mobileNo = mobileNo;
		this.email = email;
		this.password = password;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
//		this.profilePath = profilePath;
		this.isActive = isActive;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
