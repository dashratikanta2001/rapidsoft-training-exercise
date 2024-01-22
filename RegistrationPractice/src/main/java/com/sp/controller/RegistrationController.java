package com.sp.controller;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sp.dto.PasswordDto;
import com.sp.dto.UserDto;
import com.sp.entity.User;
import com.sp.entity.VerificationToken;
import com.sp.event.RegistrationCompleteEvent;
import com.sp.service.UserService;

@RestController
public class RegistrationController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/hello")
	public String helo()
	{
		return "Hello Welcone to the application";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserDto userDto, HttpServletRequest request)
	{
		User user = userService.registerUser(userDto);
		
		publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
		
		return "Success";
	}
	
	@GetMapping("/verifyRegistration")
	public String postMethodName(@RequestParam("token") String token) {
		String result = userService.validateVerificationToken(token);
		
		if (result.equalsIgnoreCase("valid")) {
			return "User Verified Successfully";
		}
		else if (result.equalsIgnoreCase("expired")) {
			return "Token expired";
		}
		else {
			return "Bad Token";
		}
	}
	
	
	@GetMapping("/resendVerifyToken")
	public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request)
	{
		VerificationToken verificationToken =userService.generateNewVerificationToken(oldToken);
		
		User user = verificationToken.getUser();
		resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
		
		return "Verification Link sent. ";
	}
	

	@PostMapping("/resetPassword")
	public String resetpassword(@RequestBody PasswordDto passwordDto, HttpServletRequest request)
	{
		User user = userService.findUserByEmail(passwordDto.getEmail());
		String url="";
		if (user!=null) {
			String token = UUID.randomUUID().toString();
			userService.createPasswordResetTokenForUser(user, token);
			url=passwordResetTokenMail(user,applicationUrl(request), token);
		}
		
		return url;
	}
	
	@PostMapping("/savePassword")
	public String savePassword(@RequestParam("token") String token,@RequestBody PasswordDto passwordDto)
	{
		String result = userService.validatePasswordResetToken(token);
		
		if (!result.equalsIgnoreCase("valid")) {
			return "Invalid Token";
		}
		Optional<User> user = userService.getUserByPasswordResetToken(token);
		if (user.isPresent()) {
			userService.changePassword(user.get(),passwordDto.getNewPassword());
			return "Password Reset Successfully";
		}
		else {
			return "Invalid Token";
		}
	}
	
	

	private String passwordResetTokenMail(User user, String applicationUrl, String token) {
		String url =applicationUrl+"/savePassword?token="+token;
		//sendVerificationEmail() implement here.
		
		System.out.println("Click the link to verify your account: \n"+url+"\n");
		return url;
	}

	private String applicationUrl(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "http://"+request.getServerName()+
				":"+
		request.getServerPort()+
		request.getContextPath();
	}
	
	private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
		
		String url =applicationUrl+"/verifyRegistration?token="+verificationToken.getToken();
		//sendVerificationEmail() implement here.
		
		System.out.println("Click the link to verify your account: \n"+url+"\n");
		
		
	}
}

