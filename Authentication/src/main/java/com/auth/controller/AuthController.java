package com.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dto.CustomResponse;
import com.auth.dto.SignupDto;
import com.auth.service.UserService;


@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<CustomResponse> signup(@Valid @RequestBody SignupDto requestDto)
	{
		CustomResponse response = userService.saveUser(requestDto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/verify")
	public ResponseEntity<CustomResponse> verifyUser(@RequestParam String email, @RequestParam String otp)
	{
		CustomResponse response =userService.verifyOtp(email, otp);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/resend-otp")
	public ResponseEntity<CustomResponse> resendOtp(@RequestParam String email) {
		CustomResponse response = userService.resendOtp(email);
		return ResponseEntity.ok(response);
	}
	
	
}
