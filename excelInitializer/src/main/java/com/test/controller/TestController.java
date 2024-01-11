package com.test.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.Service.CustomerService;

import jakarta.servlet.http.HttpServletResponse;

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
		
		return ResponseEntity.ok(Map.of("Message", "Customers data uploaded and saved to database successfully."));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllCustomers() {
		
//		customerService.getCustomers();
		return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
//		return ResponseEntity.ok(customerService.getCustomers());
	}
	
}