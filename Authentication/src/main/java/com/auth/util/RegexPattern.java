package com.auth.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.auth.exception.ApiException;

public class RegexPattern {
	
	public static boolean validEmailPattern(String email)
	{

		if (email == null || email.trim().isEmpty()) {
			throw new ApiException("Email is empty.");
		}
		String regex = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		throw new ApiException("Invalid Email format.");
	}
	
	public static boolean validPhoneNumberPattern(String phoneNo)
	{
		if (phoneNo == null || phoneNo.trim().isEmpty()) {
			throw new ApiException("Phone Number is empty.");
		}
		
		if(phoneNo.length()<10)
			throw new ApiException("Phone number must be 10 digits.");
		
		String regex = "^[6789]\\d{9}$";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNo);
		if (matcher.matches()) {
			return true;
		}
		throw new ApiException("Invalid Phone Number.");
	}
	
}
