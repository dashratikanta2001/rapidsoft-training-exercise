package com.rd.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rd.entity.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private RedisTemplate redisTemplate;	
	
	private static final String KEY="USER";
	
	@Override
	public boolean saveUser(User user) {
		try {
			redisTemplate.opsForHash().put(KEY, user.getId().toString(), user);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<User> fetchAllUser() {
		List<User> users;
		users = redisTemplate.opsForHash().values(KEY);
		return users;
	}

	@Override
	public User fetchUserById(Long id) {
		Object obj = redisTemplate.opsForHash().get(KEY, id.toString());
		User user = new ObjectMapper().convertValue(obj, User.class);
		return user;
	}

	@Override
	public boolean deleteUser(Long id) {
		try {
			redisTemplate.opsForHash().delete(KEY, id.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
