package com.rd.service;

import java.util.List;

import com.rd.entity.Role;
import com.rd.entity.User;

public interface UserService {

	User saveUser(User user);

	Role saveRole(Role role);

	void addRoleToUser(String username, String roleName);

	User getUser(String username);

	List<User> getUsers();

}
