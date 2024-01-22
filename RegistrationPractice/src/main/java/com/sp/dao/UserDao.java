package com.sp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sp.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	User findByEmail(String email);

}
