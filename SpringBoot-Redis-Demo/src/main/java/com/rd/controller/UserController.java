package com.rd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rd.entity.User;
import com.rd.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/user")
	public ResponseEntity<?> saveUser(@RequestBody User user)
	{
		boolean result = userService.saveUser(user);
		if (result) {
			return ResponseEntity.ok("User created successfully.");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}
	}
	
	
	@GetMapping("/user")
	public ResponseEntity<?> fetchAlluser()
	{
		List<User> users;
		users = userService.fetchAllUser();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> fetchUserById(@PathVariable("id") Long id)
	{
		User user;
		user = userService.fetchUserById(id);
		if (user == null) {
			return ResponseEntity.badRequest().body("Invalid id");
		}
		return ResponseEntity.ok(user);
	}
	
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id)
	{
		boolean result = userService.deleteUser(id);
		if (result) {
			return ResponseEntity.ok("User deleted successfully.");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}
	}
	
	
}
