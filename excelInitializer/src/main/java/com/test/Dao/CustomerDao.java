package com.test.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Customer;

public interface CustomerDao  extends JpaRepository<Customer, Integer>{

}
