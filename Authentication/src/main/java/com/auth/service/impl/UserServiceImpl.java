package com.auth.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.dao.UserDao;
import com.auth.dto.CustomResponse;
import com.auth.dto.SignupDto;
import com.auth.entity.User;
import com.auth.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private EmailUtil emailUtil;

	@Override
	public CustomResponse saveUser(SignupDto request) {

		User user = userDao.findUserByEmailId(request.getEmail());
		if (user != null && user.getIsActive()) {
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null,
					"Account already exist with email: " + request.getEmail());
		}
		
		user = new User();
		user.setEmail(request.getEmail());					
		user.setMobileNo(Long.parseLong(request.getMobileNo().replaceAll("[^\\d]", "")));
		user.setName(request.getName());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setCreatedOn(new Date());
		user.setIsActive(true);
		
		user = userDao.saveUser(user);		
		
		return new CustomResponse(HttpStatus.CREATED.value(), null, "User Created successfully.");
	}
	
	
	

	


	

}
