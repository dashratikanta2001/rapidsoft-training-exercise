package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Service.EmailServiceV1;


@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailServiceV1 emailService;
	
	@GetMapping("/send")
	public ResponseEntity<?> sendMail() {
	
		emailService.sendEmailToUser();
		return ResponseEntity.ok("Email sent successfully");
	}
	
}
