package com.auth.dao;

import com.auth.entity.User;

public interface UserDao {

	User saveUser(User user);

	User findUserByEmailId(String email);

}
