package com.auth.service;

import com.auth.dto.ChangePasswordDto;
import com.auth.dto.CustomResponse;
import com.auth.dto.SignupDto;

public interface UserService {

	CustomResponse saveUser(SignupDto request);

	CustomResponse changePassword(ChangePasswordDto request);

//	CustomResponse verifyOtp(String email, String otp);
//
//	CustomResponse resendOtp(String email);
	
	
}
