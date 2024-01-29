package com.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Dto.PaginationRequestDto;
import com.test.Service.CustomerService;


@RestController
@RequestMapping("/v2/customer")
public class PaginationController {

	@Autowired
	private CustomerService customerService;
	
	private final Logger logger = LoggerFactory.getLogger(PaginationController.class);
	
	@GetMapping("/pagination")
	public ResponseEntity<?> getCustomerByPagination(@RequestBody PaginationRequestDto requestDto)
	{
		
		
		return ResponseEntity.ok(customerService.getCustomersByPagination(requestDto));
	}
	@GetMapping("/")
	public ResponseEntity<?> getMethodName() {
		
		return ResponseEntity.ok("Hello");
	}
	
	
}
