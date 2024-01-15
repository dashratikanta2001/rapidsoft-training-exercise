package com.test.Service;



import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
//@EnableScheduling
public class EmailServiceV3 {

	@Autowired
	private JavaMailSender mailSender;
	
	public String sendEmail()
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ratikantadash.rt@gmail.com");
		message.setTo("ratikantadash.rt@gmail.com");
		message.setSubject("Test subject");
		message.setText("Test Body");
		mailSender.send(message);
		return "Mail sent successfully.";
	}

//	@Scheduled(fixedRate = 2000)
//	@Scheduled(fixedDelay = 3000, initialDelay = 9000)
	public String sendEmailWithAttachment() {
		// TODO Auto-generated method stub
		try {
			 MimeMessage message = mailSender.createMimeMessage();
			 
			 
			 MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
			
//			 messageHelper.setFrom("ratikantadash.rt@gmail.com");
			 messageHelper.setTo("ratikantadash.rt@gmail.com");
//			 messageHelper.setCc("manajitpradhan@gmail.com");
//			 messageHelper.setSentDate(new Date(2023, 01, 15, 3, 38));
			 messageHelper.setSubject("Test subject");
			 messageHelper.setText("Test Body");
			 
			 File file = new File("/home/rapidsoft/Downloads/background.jpg");
			 
			 messageHelper.addAttachment(file.getName(), file);
			 
			 
			 mailSender.send(message);
			 System.out.println("Sent successfully...........");
			 return "Mail sent successfully";
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Mail not sent.";
		}
	}
	
}
