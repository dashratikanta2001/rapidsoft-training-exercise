package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Dto.CustomerDto;
//import com.test.Service.EmailServiceV1;
//import com.test.Service.EmailServiceV2;
import com.test.Service.EmailServiceV3;


@RestController
@RequestMapping("/email")
public class EmailController {

//	@Autowired
//	private EmailServiceV1 emailServiceV1;
//	
//	@Autowired
//	private EmailServiceV2 emailServiceV2;
	
	@Autowired
	private EmailServiceV3 emailServiceV3;
	
//	@GetMapping("/send")
//	public ResponseEntity<?> sendMail() {
//	
//		emailServiceV1.sendEmailToUser();
//		return ResponseEntity.ok("Email sent successfully");
//	}
	
	@GetMapping("/send/v3")
	public ResponseEntity<?> sendMailV3() {
		
		
		return ResponseEntity.ok(emailServiceV3.sendEmail());
	}
	
	@GetMapping("/sendWithAttachment/v3")
	public ResponseEntity<?> sendMailWithAttachmentV3() {
		
		
		return ResponseEntity.ok(emailServiceV3.sendEmailWithAttachment());
	}
	
	
//	@PostMapping("/generateOtp")
//	public ResponseEntity<?> GenerateOtp(@RequestBody CustomerDto customerDto) {
//		
//		if (customerDto == null || customerDto.getEmail() == null || customerDto.getEmail().trim().equals("")) {
//			return ResponseEntity.badRequest().body("Please enter a valid email id.");
//		}
//		boolean otpsended = emailServiceV1.sendOtp(customerDto.getEmail());
//		
//		if (otpsended) {
//			return ResponseEntity.ok("Check your mail for otp");
//		}
//		return ResponseEntity.badRequest().body("Otp can't send to this email id.");
//	}
//	
//	@PostMapping("/v2/generateOtp")
//	public ResponseEntity<?> GenerateOtpv2(@RequestBody CustomerDto customerDto) {
//		
//		if (customerDto == null || customerDto.getEmail() == null || customerDto.getEmail().trim().equals("")) {
//			return ResponseEntity.badRequest().body("Please enter a valid email id.");
//		}
//		boolean otpsended = emailServiceV2.sendEmail(customerDto.getEmail());
//		
//		if (otpsended) {
//			return ResponseEntity.ok("Check your mail for otp");
//		}
//		return ResponseEntity.badRequest().body("Otp can't send to this email id.");
//	}
	
	
}
