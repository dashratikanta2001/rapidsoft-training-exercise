package com.rd.dao;

import java.util.List;

import com.rd.entity.User;

public interface UserDao {

	boolean saveUser(User user);

	List<User> fetchAllUser();

	User fetchUserById(Long id);

	boolean deleteUser(Long id);

}
