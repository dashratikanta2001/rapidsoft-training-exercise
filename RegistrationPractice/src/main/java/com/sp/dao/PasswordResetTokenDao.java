package com.sp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sp.entity.PasswordResetToken;

@Repository
public interface PasswordResetTokenDao extends JpaRepository<PasswordResetToken, Integer>{

	PasswordResetToken findByToken(String token);

}
