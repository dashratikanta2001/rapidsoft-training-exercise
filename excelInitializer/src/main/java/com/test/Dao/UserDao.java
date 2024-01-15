package com.test.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.entity.Customer2;

@Repository
public interface UserDao extends JpaRepository<Customer2, Integer> {

	Customer2 findByEmail(String email);

}
