package com.fbs.flight.expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceAlreadyExistException extends Exception {

	public ResourceAlreadyExistException(String message) {
		super(message);

	}
 
}