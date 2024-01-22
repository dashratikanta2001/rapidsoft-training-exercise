package com.pp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.pp.entity.Customer;

public interface CustomerDaoInterface {

	@Query("from Customer")
	List<Customer> findAllCustomer();
	
}
