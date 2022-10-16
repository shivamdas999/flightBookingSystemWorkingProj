package com.fbs.booking.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
   
//	@ExceptionHandler(value = Exception.class)
//	public ResponseEntity<ErrorDetails> handleException(Exception e){
//		
//		ErrorDetails errorDetails= new ErrorDetails();
//		errorDetails.setErrorMessage(e.getMessage());
//		errorDetails.setTimeStamp(LocalDateTime.now());
//		return  new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
//}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> rep = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
				String fieldName = ((FieldError)error).getField();
				String message = error.getDefaultMessage();
				rep.put(fieldName, message);});
		return new ResponseEntity<>(rep , HttpStatus.BAD_REQUEST);
	}
}