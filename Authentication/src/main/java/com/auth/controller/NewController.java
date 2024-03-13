package com.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.security.JwtTokenHelper;

@RestController
@RequestMapping("/test")
public class NewController {
	
	Logger LOGGER =LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JwtTokenHelper filter;
	@GetMapping
	public ResponseEntity<?> getMethodName(@RequestParam String param, HttpServletRequest request) {
		
//		System.out.println(request.getHeader("Authorization"));
		String username = filter.extractUsername(request.getHeader("Authorization").substring(7));
		
		LOGGER.info("This method is called by : "+username);
		return ResponseEntity.ok(param);
	}
	
}
