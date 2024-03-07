package com.auth.dao;

import com.auth.entity.Otp;

public interface OtpDao {

	Otp saveOtp(Otp otp);
	
	Otp findOtpByUsername(String username);
	
}
