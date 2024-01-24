package com.sp.service.impl;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sp.dao.PasswordResetTokenDao;
import com.sp.dao.UserDao;
import com.sp.dao.VerificationTokenDao;
import com.sp.dto.UserDto;
import com.sp.entity.PasswordResetToken;
import com.sp.entity.User;
import com.sp.entity.VerificationToken;
import com.sp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private VerificationTokenDao verificationTokenDao;

	@Autowired
	private PasswordResetTokenDao passwordResetTokenDao;

	@Override
	public User registerUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = new User();

		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setRole("USER");
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));

		userDao.save(user);

		return user;
	}

	@Override
	public void saveVarificationTokenForUser(String token, User user) {
		// TODO Auto-generated method stub
		VerificationToken verificationToken = new VerificationToken(user, token);
		verificationTokenDao.save(verificationToken);

	}

	@Override
	public String validateVerificationToken(String token) {
		// TODO Auto-generated method stub
		VerificationToken verificationToken = verificationTokenDao.findByToken(token);
		if (verificationToken == null) {
			return "invalid";
		}

		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if (verificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
			return "expired";
		}

		user.setEnabled(true);
		userDao.save(user);
		verificationTokenDao.delete(verificationToken);

		return "valid";
	}

	@Override
	public VerificationToken generateNewVerificationToken(String oldToken) {
		// TODO Auto-generated method stub

		VerificationToken verificationToken = verificationTokenDao.findByToken(oldToken);

		verificationToken.setToken(UUID.randomUUID().toString());
//		verificationToken.setExpirationTime(null);
		verificationTokenDao.save(verificationToken);

		return verificationToken;
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.findByEmail(email);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		// TODO Auto-generated method stub

		PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
		passwordResetTokenDao.save(passwordResetToken);
	}

	@Override
	public String validatePasswordResetToken(String token) {
		// TODO Auto-generated method stub
		PasswordResetToken passwordResetToken = passwordResetTokenDao.findByToken(token);
		if (passwordResetToken == null) {
			return "invalid";
		}

		User user = passwordResetToken.getUser();
		Calendar cal = Calendar.getInstance();
		if (passwordResetToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
			passwordResetTokenDao.delete(passwordResetToken);
			return "expired";
		}

		return "valid";
	}

	@Override
	public Optional<User> getUserByPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(passwordResetTokenDao.findByToken(token).getUser());
	}

	@Override
	public void changePassword(User user, String newPassword) {
		user.setPassword(passwordEncoder.encode(newPassword));
		userDao.save(user);
	}

	@Override
	public boolean checkIfValidOldPassword(User user, String oldPassword) {
		// TODO Auto-generated method stub
		return passwordEncoder.matches(oldPassword, user.getPassword());
	}

}
