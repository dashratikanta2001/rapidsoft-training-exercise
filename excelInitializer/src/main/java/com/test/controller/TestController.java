package com.test.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Service.CustomerService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/home")
public class TestController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<?> test()
	{
		return ResponseEntity.ok().body("Welcome");
	}
	
	@GetMapping("generate-excel")
	public ResponseEntity<?> generateExcel(HttpServletResponse response)
	{
		
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Customers_Information.xlsx";
		
		response.setHeader(headerKey, headerValue);
//		
		try {
			customerService.GenerateExcel(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in excel creation");
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body("Hello");
	}
}
