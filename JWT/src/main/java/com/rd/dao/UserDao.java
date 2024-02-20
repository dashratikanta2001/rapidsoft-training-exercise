package com.rd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rd.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
