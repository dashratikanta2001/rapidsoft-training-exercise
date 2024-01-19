package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	public ResponseEntity<?> getCustomerByPagination(@RequestBody PaginationRequestDto requestDto)
	{
		
		
		return ResponseEntity.ok(customerService.getCustomersByPagination(requestDto));
	}
	
}
