package com.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceV4 {

	@Autowired
	private JavaMailSender mailSender;
	
	
	
}
