package com.rd.service;

import java.util.List;

import com.rd.entity.User;

public interface UserService {

	boolean saveUser(User user);

	List<User> fetchAllUser();

	User fetchUserById(Long id);

	boolean deleteUser(Long id);

}
