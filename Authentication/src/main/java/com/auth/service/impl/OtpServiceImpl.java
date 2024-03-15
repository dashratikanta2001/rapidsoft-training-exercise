package com.auth.service.impl;

import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.dao.OtpDao;
import com.auth.dao.UserDao;
import com.auth.dto.CustomResponse;
import com.auth.entity.Otp;
import com.auth.entity.User;
import com.auth.exception.ApiException;
import com.auth.service.OtpService;
import com.auth.util.EmailUtil;

@Service
@Transactional
public class OtpServiceImpl implements OtpService {

	private final Long OTP_VALIDTIME = 10L;

	@Autowired
	private OtpDao otpDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public CustomResponse verifyOtp(String email, String otp) {

		Otp user = otpDao.findOtpByUsername(email);
		
//		System.out.println("OTP timer = "+user.getCreatedOn().before(new Date(System.currentTimeMillis()-user.getValidTime() *60*1000)));
		
		if (user == null) {
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Invalid OTP");
		} else if (user.getIsVerified() && !user.getIsActive()) {
			return new CustomResponse(HttpStatus.CONFLICT.value(), null, "Account already exist in this Email id");
		}
//		else if(new Date(user.getValidTime()+(10*60*1000)).before(new Date(System.currentTimeMillis())))
		else if (passwordEncoder.matches(otp, user.getOtp()) && !user.getCreatedOn().after(new Date(System.currentTimeMillis()-user.getValidTime() *60*1000))) {
//			System.out.println("OTP timer = "+user.getCreatedOn().compareTo(new Date(System.currentTimeMillis()-10*60*1000)));
//			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "OTP expired.");
			return new CustomResponse(HttpStatus.UNAUTHORIZED.value(), null, "OTP expired.");
		}
//		else if(!otp.equals(user.getOtp()))
		else if (!passwordEncoder.matches(otp, user.getOtp())) {
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Invalid OTP");
		}

		user.setIsVerified(true);
		user.setIsActive(false);
		otpDao.saveOtp(user);

		return new CustomResponse(HttpStatus.OK.value(), null, "OTP verified successfully.");
	}

	@Override
	public CustomResponse sendOtp(String email, Boolean forgotOTP) {
		Otp otpEntity ;

		otpEntity = otpDao.findOtpByUsername(email);
		if (otpEntity != null && !forgotOTP) {
			
//			else {
				if (otpEntity.getIsVerified() && !otpEntity.getIsActive()) {
					return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Account already exist in this Email id");
				}
				
//			}
//			else if (new Date(user.getOtpTimer()+(1*60*1000)).after(new Date(System.currentTimeMillis()))) {
			
		}
		else {
			System.out.println("otp entity found");
			if(forgotOTP)
			{
				System.out.println("user entity found");
				User user = userDao.findUserByEmailId(email);
				if(user==null)
				{
					return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Account not exist with Email id.");
				}
			}
			if(otpEntity == null)
			otpEntity = new Otp();
		}
		if(otpEntity!= null && otpEntity.getCreatedOn() != null)
		{
//			System.out.println("HELOOOOOOOOOOOOOOOOOOOOOO"+otpEntity.getCreatedOn());
			if (otpEntity.getCreatedOn().after(new Date(System.currentTimeMillis() - (1 * 30 * 1000)))) {
				throw new ApiException("Please wait for 30 seconds to resend the otp.");
			}
		}

		String generatedOTP = generateOTP();System.out.println("OTP = "+generatedOTP);
		otpEntity.setOtp(passwordEncoder.encode(generatedOTP));
		otpEntity.setEmail(email);
		otpEntity.setCreatedOn(new Date());
		otpEntity.setValidTime(OTP_VALIDTIME);
		otpEntity.setIsVerified(false);
		otpEntity.setIsActive(true);
		otpDao.saveOtp(otpEntity);
		System.out.println("Email = "+email+" OTP = "+generatedOTP);
		
//		Thread emailThread = new Thread(() -> {
			sendOtpVerificationEmail(otpEntity.getEmail(), generatedOTP);
//        });
//        emailThread.start();
		
		//sendOtpVerificationEmail(otpEntity.getEmail(), generatedOTP);
		
		
		return new CustomResponse(HttpStatus.OK.value(), null, "OTP sent successfully.");
	}

	private String generateOTP() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}

	private void sendOtpVerificationEmail(String email, String otp) {
		String subject = "Email Verification";
		String body = EmailUtil.otpBody(email, otp);

		Thread emailThread = new Thread(() -> {
			emailUtil.sendEmail(email, subject, body);
			
		});
		emailThread.start();

	}

}
