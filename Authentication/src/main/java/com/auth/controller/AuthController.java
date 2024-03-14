package com.auth.controller;

import static com.auth.util.RegexPattern.validEmailPattern;
import static com.auth.util.RegexPattern.validPhoneNumberPattern;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.auth.dto.ChangePasswordDto;
import com.auth.dto.CustomResponse;
import com.auth.dto.JwtAuthRequest;
import com.auth.dto.JwtAuthResponse;
import com.auth.dto.SignupDto;
import com.auth.exception.ApiException;
import com.auth.exception.LoginApiException;
import com.auth.security.JwtTokenHelper;
import com.auth.service.OtpService;
import com.auth.service.UserService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	
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
	public ResponseEntity<CustomResponse> createToken(@Valid @RequestBody JwtAuthRequest request, HttpServletRequest httpRequest) throws Exception {
//		System.out.println("Ip Address  = "+httpRequest.get);

		if(request != null && request.getUsername()== null || request.getPassword() == null || request.getUsername().length()==0 || request.getPassword().length() == 0)
		{
//			return ResponseEntity.ok(new CustomResponse(HttpStatus.UNAUTHORIZED.value(), null, "Invalid Cridentials"));
			throw new LoginApiException("Invalid Cridentials");
		}
		
		validEmailPattern(request.getUsername());
		
		LOGGER.info("-> "+Thread.currentThread().getStackTrace()[1].getMethodName());
		
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
		LOGGER.info("-> "+Thread.currentThread().getStackTrace()[1].getMethodName());
		CustomResponse response = otpService.verifyOtp(requestDto.getEmail(), requestDto.getOtp());
		if(response.getStatus().equals(HttpStatus.OK.value()))
		{
			System.out.println("Password = "+requestDto.getPassword());
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
	public ResponseEntity<?> resendOtp(@RequestParam String email) {
		if(!validEmailPattern(email))
			return ResponseEntity.ok(new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Email can't be blank"));
		LOGGER.info("-> "+Thread.currentThread().getStackTrace()[1].getMethodName());
		CustomResponse response = otpService.sendOtp(email,false);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/forgot-password/send-otp")
	public ResponseEntity<?> checkEmailForForgotPassword(@RequestParam String email) {
		
		if(!validEmailPattern(email))
			return ResponseEntity.ok(new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Email can't be blank"));
		LOGGER.info("-> "+Thread.currentThread().getStackTrace()[1].getMethodName());
		CustomResponse response = otpService.sendOtp(email,true);
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Verify the otp for forgot password")
	@PostMapping("/forgot-password/verify-otp")
	public ResponseEntity<?> forgotPasswordVerifyOtp(@RequestParam(required = true) String email, @RequestParam(required = true) String otp) {
		
		if(!validEmailPattern(email))
			return ResponseEntity.ok(new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Email can't be blank"));
		if(otp == null || otp.length()<=0)
			return ResponseEntity.ok(new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "OTP can't be blank"));
			
		LOGGER.info("-> "+Thread.currentThread().getStackTrace()[1].getMethodName());
		CustomResponse response = otpService.verifyOtp(email, otp);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/forgot-password/change-password")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto request) {
		validEmailPattern(request.getEmail());
		LOGGER.info("-> "+Thread.currentThread().getStackTrace()[1].getMethodName());
		
		CustomResponse response = userService.changePassword(request);
		
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
//			throw new ApiException("Invalid Cridentials");
			throw new LoginApiException("Invalid Cridentials");
		}

	}
	
	
}
