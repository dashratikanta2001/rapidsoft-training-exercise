package com.test.Service;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class EmailServiceV1 {

	@Autowired
	private Environment env;
	
	public void sendEmailToUser() {
		System.out.println("preparing to send message....");
		String message = "Hello. This is security check.";
		String subject = "Test mail";
		String to = "ratikantadash.rt@gmail.com";
//		String to = "hokirar795@telvetto.com";
		String from = env.getProperty("spring.mail.username");
		sendEmail(message, subject, to, from);
		
	}

	private void sendEmail(String message, String subject, String to, String from) {

		//variable for gmail;
//		String host = "smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES = "+properties);
		
		//Setting important information to properties object.
		
		//host set
		
		properties.put("mail.smtp.host", env.getProperty("spring.mail.host"));
		properties.put("mail.smtp.port", env.getProperty("spring.mail.port"));
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//STEP 1 : to get the session object
		Session session = Session.getInstance(properties,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
//				System.out.println("Passsword = "+env.getProperty("spring.mail.password") );
				return new PasswordAuthentication(env.getProperty("spring.mail.username"), env.getProperty("spring.mail.password"));
			}
			
			
		});
		
//		session.setDebug(true);
		
		//STEP 2: compose the message [text, multimedia]
		
		MimeMessage m = new MimeMessage(session);
		
		try {
			//from email
			m.setFrom(from);
			
			//adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			m.setSubject(subject);
			
			//adding text to message
//			m.setText(message);
			
			//adding html content to message.
//			m.setContent(
//		              "\n"
//		              + "\n"
//		              + "<h1>Dear Manajit,</h1>\n"
//		              + "<p>Somebody trying to change your account password</p>\n"
//		              + "<p>If not you then please click here.</p>\n"
//		              + "<a href=\"https://google.com\">\n"
//		              + "<input type=\"submit\" style=\"background-color:#dc3545; color :white; height:30px;width:80px\" value=\"Not me\"></input></a>\n"
//		              + "<a href=\"https://youtube.com\">\n"
//		              + "<input type=\"submit\" style=\"background-color:#198754; color :white; height:30px;width:80px\" value=\"Yes me\"></input></a>\n",
//		             "text/html");
			
			
			//adding attachment to the message
			String path = "/home/rapidsoft/Downloads/recheck-image.jpeg";
			
			
			MimeMultipart mimeMultipart = new MimeMultipart();
			
			//TEXT
			
			
			MimeBodyPart textMime = new MimeBodyPart();
			
			
			//file
			
			MimeBodyPart fileMime = new MimeBodyPart();
			try {
				textMime.setText(message);
				File file = new File(path);
				fileMime.attachFile(file);
				
				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMime);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			m.setContent(mimeMultipart);
			
			//send
			
			//STEP 3: send the message using Transport class
			Transport.send(m);
			System.out.println("Sent success...............");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}




























