package com.fbs.authentication.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fbs.authentication.exception.PassangerNotFoundException;
import com.fbs.authentication.exception.RoleNotFoundException;
import com.fbs.authentication.models.MyErrorResponse;



@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler({RoleNotFoundException.class})
	public ResponseEntity<MyErrorResponse> handleProductNotFound(RoleNotFoundException ex){
				MyErrorResponse error=new MyErrorResponse();
//		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		error.setReason("id doesn't exist....");
		return new ResponseEntity<MyErrorResponse>(error,HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> rep = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
				String fieldName = ((FieldError)error).getField();
				String message = error.getDefaultMessage();
				rep.put(fieldName, message);});
		return new ResponseEntity<>(rep,HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<MyErrorResponse> handleMethodNotSupportException(HttpRequestMethodNotSupportedException ex){
				MyErrorResponse error=new MyErrorResponse();
//		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
		error.setMessage(ex.getMessage());
		error.setReason("Wrong method selected....");
		return new ResponseEntity<MyErrorResponse>(error,HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleException(Exception ex){
			Map<String,Object> body=new LinkedHashMap<String, Object>();
//		body.put("timestamp",LocalDateTime.now());
		body.put("Status",HttpStatus.NOT_ACCEPTABLE);
		body.put("Message",ex.getMessage());
		body.put("Reason","May be resource not found....");
		return new ResponseEntity<Object>(body,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({PassangerNotFoundException.class})
	public ResponseEntity<Object> handleException(NotFoundException ex){
			Map<String,Object> body=new LinkedHashMap<String, Object>();
		String msg = "No Passaneger Exist";
//		body.put("timestamp",LocalDateTime.now());
		body.put("Status",HttpStatus.NOT_FOUND);
		body.put("Message",msg);
		body.put("Reason","");
		return new ResponseEntity<Object>(body,HttpStatus.NOT_ACCEPTABLE);
	}
}