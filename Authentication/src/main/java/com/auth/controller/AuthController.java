package com.auth.controller;

import static com.auth.util.RegexPattern.validEmailPattern;
import static com.auth.util.RegexPattern.validPhoneNumberPattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dto.CustomResponse;
import com.auth.dto.JwtAuthRequest;
import com.auth.dto.JwtAuthResponse;
import com.auth.dto.SignupDto;
import com.auth.exception.ApiException;
import com.auth.security.JwtTokenHelper;
import com.auth.service.OtpService;
import com.auth.service.UserService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private OtpService otpService;

	@PostMapping("/login")
	public ResponseEntity<CustomResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		validEmailPattern(request.getUsername());
		
		this.authenticate(request.getUsername(), request.getPassword());

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();

		response.setUsername(userDetails.getUsername());
		response.setToken(token);
		CustomResponse customResponse = new CustomResponse(HttpStatus.OK.value(), response, "SUCCESS");
		

		return ResponseEntity.ok(customResponse);
	}

	@PostMapping("/signup")
	public ResponseEntity<CustomResponse> signup(@Valid @RequestBody SignupDto requestDto)
	{
		validEmailPattern(requestDto.getEmail());
		validPhoneNumberPattern(requestDto.getMobileNo());
		CustomResponse response = otpService.verifyOtp(requestDto.getEmail(), requestDto.getOtp());
		if(response.getStatus().equals(HttpStatus.OK.value()))
		{
			response = userService.saveUser(requestDto);
		}
		
		return ResponseEntity.ok(response);
	}
	
//	@PostMapping("/verify")
//	public ResponseEntity<CustomResponse> verifyUser(@RequestParam String email, @RequestParam String otp)
//	{
//		CustomResponse response =userService.verifyOtp(email, otp);
//		return ResponseEntity.ok(response);
//	}
	
	@PostMapping("/send-otp")
	public ResponseEntity<CustomResponse> resendOtp(@RequestParam String email) {
		validEmailPattern(email);
		CustomResponse response = otpService.sendOtp(email,false);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/forgot-password/send-otp")
	public ResponseEntity<?> checkEmailForForgotPassword(@RequestParam String email) {
		
		validEmailPattern(email);
		CustomResponse response = otpService.sendOtp(email,true);
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Verify the otp for forgot password")
	@PostMapping("/forgot-password/verify-otp")
	public ResponseEntity<?> postMethodName(@RequestParam String email, @RequestParam String otp) {
		
		validEmailPattern(email);
		CustomResponse response = otpService.verifyOtp(email, otp);
		
		response =new CustomResponse(HttpStatus.OK.value(), null, "Implement in future");
		return ResponseEntity.ok(response);
	}
	
	
	
	private void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {
			this.authenticationManager.authenticate(authenticationToken);
			
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new ApiException("Invalid username: "+username+" or password : "+password);
		}

	}
	
	
}
