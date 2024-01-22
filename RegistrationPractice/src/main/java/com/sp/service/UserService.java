package com.sp.service;

import java.util.Optional;

import com.sp.dto.UserDto;
import com.sp.entity.User;
import com.sp.entity.VerificationToken;

public interface UserService {

	User registerUser(UserDto userDto);

	void saveVarificationTokenForUser(String token, User user);

	String validateVerificationToken(String token);

	VerificationToken generateNewVerificationToken(String oldToken);

	User findUserByEmail(String email);

	void createPasswordResetTokenForUser(User user, String token);

	String validatePasswordResetToken(String token);

	Optional<User> getUserByPasswordResetToken(String token);

	void changePassword(User user, String newPassword);

}
