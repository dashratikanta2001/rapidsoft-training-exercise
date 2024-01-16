package com.test.Service;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.test.Dao.UserDao;
import com.test.entity.Customer2;
import com.test.response.RegisterRequest;
import com.test.response.RegisterResponse;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public RegisterResponse register(RegisterRequest registerRequest) {
		// TODO Auto-generated method stub
		Customer2 existingUser = userDao.findByEmail(registerRequest.getEmail());

		if (existingUser != null && existingUser.isVarified()) {
			throw new RuntimeException("User Already Registered");
		}

		Customer2 customer = new Customer2();
		customer.setUserName(registerRequest.getUserName());
		customer.setEmail(registerRequest.getEmail());
		customer.setPassword(registerRequest.getPassword());
		String otp = generateOTP();
		customer.setOtp(otp);
		customer.setOtpTimer(System.currentTimeMillis());
		Customer2 savedUser = userDao.save(customer);
		sendVerificationEmail(savedUser.getEmail(), otp);
		RegisterResponse registerResponse = new RegisterResponse(savedUser.getUserName(), savedUser.getEmail());

		return registerResponse;
	}
	
	
	@Override
	public void verify(String email, String otp) {
		// TODO Auto-generated method stub
		Customer2 customer = userDao.findByEmail(email);
		if (customer == null) {
			throw new RuntimeException("User not found");
		}
		else if (customer.isVarified()) {
			throw new RuntimeException("User already varified");
		}
		//The otp will valid for 10 minutes only.
		else if (new Date(customer.getOtpTimer()+(10*60*1000)).before(new Date(System.currentTimeMillis()))) {
			System.out.println("Timer = ");
			System.out.println(System.currentTimeMillis()+(30*60*1000));
			System.out.println(customer.getOtpTimer());
			throw new RuntimeException("OTP expired.");
		} 
		else if(otp.equals(customer.getOtp()))
		{
			customer.setVarified(true);
			userDao.save(customer);
		}
		else {
			throw new RuntimeException("Invalid OTP");
		}
		
	}

	
	
	private String generateOTP()
	{
		Random random = new Random();
		int otp = 100000+random.nextInt(999999);
		return String.valueOf(otp);
	}
	
	public void sendEmail(String to, String subject, String body)
	{
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);
			
			javaMailSender.send(message);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Otp not send.");
		}
		
	}
	
	private void sendVerificationEmail(String email, String otp)
	{
		String subject = "Email Verification";
		String body = "Your verification otp is :"+otp;
		
		sendEmail(email, subject, body);
		
	}


	@Override
	public void resendOtp(String email) {
		// TODO Auto-generated method stub
		Customer2 customer = userDao.findByEmail(email);
		if (customer == null) {
			throw new RuntimeException("Invalid Email id");
		}
		else if (customer.isVarified()) {
			throw new RuntimeException("User already verified. So otp can't be resend.");
		}
		else if (new Date(customer.getOtpTimer()+(1*60*1000)).after(new Date(System.currentTimeMillis()))) {
			throw new RuntimeException("Please wait for 1 minute to resend the otp.");
		}
		else
		{
			String otp = generateOTP();
			customer.setOtp(otp);
			customer.setOtpTimer(System.currentTimeMillis());
			Customer2 savedUser = userDao.save(customer);
			sendVerificationEmail(savedUser.getEmail(), otp);
			
		}
		
	}

	
}

















