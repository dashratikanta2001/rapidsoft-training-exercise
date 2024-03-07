package com.auth.service;

import com.auth.dto.CustomResponse;

public interface OtpService {

	CustomResponse verifyOtp(String email, String otp);

	CustomResponse sendOtp(String email);
}
