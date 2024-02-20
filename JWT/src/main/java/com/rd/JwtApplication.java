package com.rd;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rd.entity.Role;
import com.rd.entity.User;
import com.rd.service.UserService;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner runner(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "Ratikanta Dash", "ratikanta", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Muktikanta Dash", "muktikanta", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Manajit Prashan", "manajit", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Sandeep Bali", "sandeep", "1234", new ArrayList<>()));

			userService.addRoleToUser("ratikanta", "ROLE_USER");
			userService.addRoleToUser("ratikanta", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("ratikanta", "ROLE_ADMIN");
			userService.addRoleToUser("muktikanta", "ROLE_MANAGER");
			userService.addRoleToUser("manajit", "ROLE_ADMIN");
			userService.addRoleToUser("sandeep", "ROLE_USER");
		};
	}
}
