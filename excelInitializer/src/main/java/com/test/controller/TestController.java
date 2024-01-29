package com.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.Service.CustomerService;
import com.test.entity.Customer1;

@RestController
@RequestMapping("/customer")
public class TestController {

	@Autowired
	private CustomerService customerService;
	
//	@GetMapping
//	public ResponseEntity<?> test()
//	{
//		return ResponseEntity.ok().body("Welcome");
//	}
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@GetMapping("generate-excel")
	public ResponseEntity<?> generateExcel(HttpServletResponse response)
	{
		
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Customers_Information.xlsx";
		
		response.setHeader(headerKey, headerValue);
//		
		try {
//			customerService.GenerateExcelV1(response);
			customerService.GenerateExcelV2(response);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in excel creation");
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body("Hello");
	}
	
	@PostMapping("/upload-customers-data")
	public ResponseEntity<?> uploadCustomerData(@RequestParam("file") MultipartFile file)
	{
		customerService.saveCustomersToDatabaseV1(file);
		
		return ResponseEntity.ok("");
	}
	 
	@PostMapping("/upload-json-data")
	public ResponseEntity<?> postMethodName(@RequestBody List<Customer1> customer) {
		//TODO: process POST request
		
		List<Customer1> customer2 =customerService.saveCustomerJsonToDbV1(customer);
		
		return ResponseEntity.ok(customer2);
	}
	
	
	@GetMapping
	public ResponseEntity<?> getAllCustomers() {
//		int i=10/0;
		return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
	}
	
}
