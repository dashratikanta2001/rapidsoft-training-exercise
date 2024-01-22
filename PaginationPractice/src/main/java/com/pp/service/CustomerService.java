package com.pp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pp.dao.CustomerDao;
import com.pp.dao.CustomerDaoInterface;
import com.pp.dto.PaginationRequestDto;
import com.pp.dto.PaginationResponseDto;
import com.pp.entity.Customer;
import com.pp.response.CustomResponse;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CustomerDaoInterface customerDaoInterface;
	
	public List<Customer> findAll()
	{
//		return customerDao.findAll();
		
		return customerDaoInterface.findAllCustomer();
	}

	public CustomResponse<?> findCustomerByPagination(PaginationRequestDto requestDto) {
		// TODO Auto-generated method stub
		if (requestDto.getPage() <0 || requestDto.getSize() <0) {
			throw new RuntimeException("Page number or page size can't be less than 0");
		}
		
		PaginationResponseDto<?> list = customerDao.findAllByPagination(requestDto);
		
		
		return new CustomResponse<>("Data Found", list, HttpStatus.OK.value());
	}
}
