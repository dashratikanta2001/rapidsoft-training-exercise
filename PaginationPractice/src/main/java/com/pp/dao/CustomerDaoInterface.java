package com.pp.dao;

import java.util.List;

//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;

import com.pp.entity.Customer;

//@Repository
public interface CustomerDaoInterface{

//	@Query("from Customer")
	List<Customer> findAllCustomer();
	
}
