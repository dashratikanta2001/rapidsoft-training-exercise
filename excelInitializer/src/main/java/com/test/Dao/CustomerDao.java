package com.test.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Customer;
import com.test.util.SortOrder;

public interface CustomerDao extends JpaRepository<Customer, Integer>{

	/*
	 * List<Customer> findCustomers(int page, int size, SortOrder sortOrder);
	 * 
	 * List<Customer> findAll();
	 * 
	 * void saveAll(List<Customer> customers);
	 */

}
