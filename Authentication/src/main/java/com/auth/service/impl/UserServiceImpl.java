package com.auth.service.impl;

import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.dao.UserDao;
import com.auth.dto.CustomResponse;
import com.auth.dto.SignupDto;
import com.auth.entity.User;
import com.auth.service.UserService;
import com.auth.util.EmailUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailUtil emailUtil;

	@Override
	public CustomResponse saveUser(SignupDto request) {

		User user = userDao.findUserByEmailId(request.getEmail());
		if (user != null) {
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null,
					"Email id already exist with email: " + request.getEmail());
		}
		
		user = new User();
		user.setEmail(request.getEmail());
		user.setMobileNo(Long.parseLong(request.getMobileNo()));
		user.setName(request.getName());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setCreatedOn(new Date());
		String generatedOTP = generateOTP();
		user.setOtp(generatedOTP);
		user.setOtpTimer(System.currentTimeMillis());
		user.setVerified(false);
		
		user = userDao.saveUser(user);
		
		sendOtpVerificationEmail(user.getEmail(), generatedOTP);
		
		
		return new CustomResponse(HttpStatus.CREATED.value(), null, "User Created successfully. Please verify the account with the OTP send to your email : "+user.getEmail());
	}
	
	
	@Override
	public CustomResponse verifyOtp(String email, String otp) {
		
		User user = userDao.findUserByEmailId(email);
		if(user == null)
		{
			return new CustomResponse(HttpStatus.NOT_FOUND.value(), null, "Invalid Email");
		}
		else if(user.getVerified())
		{
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Email already verified");
		}
		else if(new Date(user.getOtpTimer()+(10*60*1000)).before(new Date(System.currentTimeMillis())))
		{
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "OTP expired.");
		}
		else if(!otp.equals(user.getOtp()))
		{
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Invalid OTP");
		}
		
		user.setVerified(true);
		user.setIsActive(true);
		userDao.saveUser(user);
		
		return new CustomResponse(HttpStatus.OK.value(), null, "OTP verified successfully.");
	}
	
	@Override
	public CustomResponse resendOtp(String email) {
		User user = userDao.findUserByEmailId(email);
		if(user == null)
		{
			return new CustomResponse(HttpStatus.NOT_FOUND.value(), null, "Invalid Email");
		}
		else if(user.getVerified())
		{
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Email already verified");
		}
		else if (new Date(user.getOtpTimer()+(1*60*1000)).after(new Date(System.currentTimeMillis()))) {
			throw new RuntimeException("Please wait for 1 minute to resend the otp.");
		}
		return null;
	}
	
	
	
	private String generateOTP()
	{
		Random random = new Random();
		int otp = 100000+random.nextInt(999999);
		return String.valueOf(otp);
	}
	
	private void sendOtpVerificationEmail(String email, String otp)
	{
		String subject = "Email Verification";
		String body = EmailUtil.otpBody(otp);
		
		emailUtil.sendEmail(email, subject, body);
		
	}


	


	

}
