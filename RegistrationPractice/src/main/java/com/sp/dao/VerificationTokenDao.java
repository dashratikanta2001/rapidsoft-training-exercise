package com.sp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sp.entity.VerificationToken;

@Repository
public interface VerificationTokenDao extends JpaRepository<VerificationToken, Integer>{

	VerificationToken findByToken(String token);

}
 