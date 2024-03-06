package com.auth.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.auth.exception.ApiException;

@Service
public class EmailUtil {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendEmail(String to, String subject, String body)
	{
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			javaMailSender.send(message);
			
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("Opt not sent.");
		}
	}
	
	public static String otpBody(String otp)
	{
		return "<!DOCTYPE html>\n"
				+ "<html lang=\"en\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Email Verification OTP</title>\n"
				+ "    <style>\n"
				+ "        body {\n"
				+ "            font-family: Arial, sans-serif;\n"
				+ "            background-color: #f2f2f2;\n"
				+ "            margin: 0;\n"
				+ "            padding: 0;\n"
				+ "        }\n"
				+ "        .container {\n"
				+ "            max-width: 600px;\n"
				+ "            margin: 20px auto;\n"
				+ "            padding: 20px;\n"
				+ "            background-color: #ffffff;\n"
				+ "            border-radius: 8px;\n"
				+ "            box-shadow: 0 0 10px rgba(0,0,0,0.1);\n"
				+ "        }\n"
				+ "        .header {\n"
				+ "            text-align: center;\n"
				+ "            margin-bottom: 20px;\n"
				+ "        }\n"
				+ "        .header h1 {\n"
				+ "            color: #333333;\n"
				+ "        }\n"
				+ "        .content {\n"
				+ "            padding: 20px;\n"
				+ "            background-color: #f9f9f9;\n"
				+ "            border-radius: 8px;\n"
				+ "            margin-bottom: 20px;\n"
				+ "        }\n"
				+ "        .otp {\n"
				+ "            font-size: 36px;\n"
				+ "            font-weight: bold;\n"
				+ "            color: #007bff;\n"
				+ "            text-align: center;\n"
				+ "            margin-bottom: 20px;\n"
				+ "        }\n"
				+ "        .footer {\n"
				+ "            text-align: center;\n"
				+ "            color: #666666;\n"
				+ "            font-size: 14px;\n"
				+ "        }\n"
				+ "    </style>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "    <div class=\"container\">\n"
				+ "        <div class=\"header\">\n"
				+ "            <h1>Email Verification OTP</h1>\n"
				+ "        </div>\n"
				+ "        <div class=\"content\">\n"
				+ "            <p>Your one-time password (OTP) for email verification is:</p>\n"
				+ "            <div class=\"otp\">\n"
				+ "                <!-- Insert OTP dynamically here -->\n"
				+ "                "+otp+"\n"
				+ "            </div>\n"
				+ "            <p>Please use this OTP to verify your email address. This OTP is valid for 10 Minutes.</p>\n"
				+ "            <p>If you didn't request this verification, you can safely ignore this email.</p>\n"
				+ "        </div>\n"
				+ "        <div class=\"footer\">\n"
				+ "            <p>&copy; 2024 Your Company Name. All rights reserved.</p>\n"
				+ "        </div>\n"
				+ "    </div>\n"
				+ "</body>\n"
				+ "</html>\n"
				+ "";
	}
}
