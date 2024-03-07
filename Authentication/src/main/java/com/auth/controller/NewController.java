package com.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class NewController {

	@GetMapping
	public ResponseEntity<?> getMethodName(@RequestParam String param) {
		return ResponseEntity.ok(param);
	}
	
}
