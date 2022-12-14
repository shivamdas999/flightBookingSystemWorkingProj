package com.fbs.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class PlaceNotFoundException extends Exception{
    
	public PlaceNotFoundException(String message) {
		super(message);
	}
}