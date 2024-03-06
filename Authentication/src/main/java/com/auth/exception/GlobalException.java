package com.auth.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth.dto.CustomResponse;


@RestControllerAdvice
public class GlobalException {

//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
//	{
//		String message = ex.getMessage();
//		ApiResponse apiResponse = new ApiResponse(message, false);
//
//		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
//	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String, String> resp = new HashMap<String, String>();

		ex.getBindingResult().getAllErrors().forEach((error) ->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);

		});
		CustomResponse response = new CustomResponse(HttpStatus.BAD_REQUEST.value(), resp, "SUCCESS");

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<CustomResponse> apiExceptionHandler(ApiException ex)
	{
		String message = ex.getMessage();
		CustomResponse response = new CustomResponse(HttpStatus.BAD_REQUEST.value() ,null, message);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> NoResourceFoundExceptionHandler(Exception ex)
//	{
//		System.out.println("This is global exception");
//		String message = ex.getMessage();
//		ApiResponse apiResponse = new ApiResponse(message, false);
//
//		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
//	}
}
