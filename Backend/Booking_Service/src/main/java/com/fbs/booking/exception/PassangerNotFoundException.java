package com.fbs.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PassangerNotFoundException extends Exception {

	public PassangerNotFoundException(String message) {
		super(message);

	}
 
}
