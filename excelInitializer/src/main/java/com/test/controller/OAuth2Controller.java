package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/oauth/")
public class OAuth2Controller {

	@GetMapping("/")
	public String home() {
		return "Hello";
	}
	
	@GetMapping("/secured")
	public String secured()
	{
		return "Hello, Secured.";
	}
	
}
