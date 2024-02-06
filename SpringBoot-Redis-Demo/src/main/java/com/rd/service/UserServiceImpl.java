package com.rd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rd.dao.UserDao;
import com.rd.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		return userDao.saveUser(user);
	}

	@Override
	public List<User> fetchAllUser() {
		// TODO Auto-generated method stub
		return userDao.fetchAllUser();
	}

	@Override
	public User fetchUserById(Long id) {
		// TODO Auto-generated method stub
		return userDao.fetchUserById(id);
	}

	@Override
	public boolean deleteUser(Long id) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(id);
	}

}
