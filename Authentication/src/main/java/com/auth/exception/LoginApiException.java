package com.auth.exception;

public class LoginApiException extends RuntimeException {

	public LoginApiException() {
		super();
	}

	public LoginApiException(String message) {
		super(message);
	}

}
