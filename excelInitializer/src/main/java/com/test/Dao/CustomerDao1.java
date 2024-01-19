package com.test.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.entity.Customer;
import com.test.entity.Customer1;

@Repository
public interface CustomerDao1  extends JpaRepository<Customer1, Integer>{

}
