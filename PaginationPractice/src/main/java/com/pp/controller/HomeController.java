package com.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pp.dto.PaginationRequestDto;
import com.pp.response.CustomResponse;
import com.pp.service.CustomerService;

@RestController
@RequestMapping("/")
public class HomeController {

	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/hello")
	public ResponseEntity<?> home()
	{
		System.out.println("Hello");
		
		return ResponseEntity.ok(customerService.findAll());
		
//		return ResponseEntity.ok("Hello This is the best practice example.");
	}
	
	@GetMapping
	public String hh()
	{
		return "Hello";
	}
	
	@GetMapping("/customer/pagination")
	public ResponseEntity<?> getCustomerByPagination(@RequestBody PaginationRequestDto requestDto)
	{
		CustomResponse<?> response = customerService.findCustomerByPagination(requestDto);
		
		return ResponseEntity.ok(response);
	}
	
}
