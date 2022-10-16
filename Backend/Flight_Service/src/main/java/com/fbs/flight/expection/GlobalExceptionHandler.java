package com.fbs.flight.expection;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
   
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> rep = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
				String fieldName = ((FieldError)error).getField();
				String message = error.getDefaultMessage();
				rep.put(fieldName, message);});
		return new ResponseEntity<>(rep,HttpStatus.BAD_REQUEST);
	}
}