package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.Service.UserService;
import com.test.response.RegisterRequest;
import com.test.response.RegisterResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest)
	{
		try {
			RegisterResponse register = userService.register(registerRequest);
			return ResponseEntity.ok(register);
			
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/verify")
	public ResponseEntity<?> verifyUser(@RequestParam String email, @RequestParam String otp)
	{
		
		try {
			userService.verify(email, otp);
			return ResponseEntity.ok("User verified successfull");
		} catch (RuntimeException e) {
			// TODO: handle exception
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/resend-otp")
	public ResponseEntity<?> resendOtp(@RequestParam String email) {
		//TODO: process POST request
		try {
			userService.resendOtp(email);
			return ResponseEntity.ok("Otp Resend to mail");
		} catch (RuntimeException e) {
			// TODO: handle exception
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
}
