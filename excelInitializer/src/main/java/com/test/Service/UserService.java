package com.test.Service;

import com.test.response.RegisterRequest;
import com.test.response.RegisterResponse;

public interface UserService {

	RegisterResponse register (RegisterRequest registerRequest);
	
	
	void verify(String email, String otp);


	void resendOtp(String email);
	
	
}
