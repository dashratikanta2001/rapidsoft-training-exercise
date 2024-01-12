package com.test.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Customer;
import com.test.entity.Customer1;

public interface CustomerDao1  extends JpaRepository<Customer1, Integer>{

}
