package com.social.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception eu,WebRequest req){
		ErrorDetails errorDetails=new ErrorDetails(eu.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> userrExceptionHandler(Exception eu,WebRequest req){
		ErrorDetails errorDetails=new ErrorDetails(eu.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
}
