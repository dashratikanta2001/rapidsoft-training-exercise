package com.rd.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rd.dao.RoleDao;
import com.rd.dao.UserDao;
import com.rd.entity.Role;
import com.rd.entity.User;
import com.rd.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.findByUsername(username);
		if (user == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		} else {
			log.info("User found in database: {}", username);

		}

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	@Override
	public User saveUser(User user) {
		log.info("Saving new User {} to database", user.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving new Role {} to database", role.getName());
		return roleDao.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("Adding role {} to user {}", roleName, username);
		User user = userDao.findByUsername(username);
		Role role = roleDao.findByName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public User getUser(String username) {
		log.info("Fetching user {} ", username);
		return userDao.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching all users ");
		return userDao.findAll();
	}

}
