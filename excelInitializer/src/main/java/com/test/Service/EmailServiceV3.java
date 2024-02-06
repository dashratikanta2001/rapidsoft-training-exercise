package com.test.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.test.entity.Customer;

@Service
//@EnableScheduling
public class EmailServiceV3 {
	private LocalDate currentDate = LocalDate.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	@Autowired
	private JavaMailSender mailSender;

	public String sendEmail() {
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

			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

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

	public void sendEmailWithPassV2(ByteArrayInputStream pass, Customer customer) {
		// TODO Auto-generated method stub
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			byte[] byteArray = new byte[pass.available()];
			pass.read(byteArray);

			messageHelper.setTo(customer.getEmail());
			messageHelper.setSubject("Pass Generated");

			messageHelper
					.setText("<h2>Hey " + customer.getName() + ",</h2>" + "<p>Your Pass is generated successfully.</p>"
							+ "<p>Now you can download your pass below and use it.</p>", true);

			messageHelper.addAttachment("Pass" + customer.getName() + "-" + currentDate.format(formatter) + ".pdf",
					new ByteArrayDataSource(byteArray, "application/pdf"));


			mailSender.send(message);
			System.out.println("Mail send successfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void sendEmailWithPass(ByteArrayOutputStream pass, Customer customer) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

			// Read PDF content into a byte array
			byte[] byteArray = pass.toByteArray();
			messageHelper.setTo(customer.getEmail());
			messageHelper.setSubject("Pass Generated");

			// Set the email body
			String emailBody = "<h2>Hey " + customer.getName() + ",</h2>"
					+ "<p>Your Pass is generated successfully.</p>"
					+ "<p>Now you can download your pass below and use it.</p>";
			messageHelper.setText(emailBody, true);

			DataSource dataSource = new ByteArrayDataSource(byteArray, "application/pdf");
			messageHelper.addAttachment("pass.pdf", dataSource);
			// Send the email
			mailSender.send(message);
			System.out.println("Mail sent successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
