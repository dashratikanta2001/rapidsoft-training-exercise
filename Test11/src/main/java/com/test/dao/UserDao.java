package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.model.User;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long>{

}
