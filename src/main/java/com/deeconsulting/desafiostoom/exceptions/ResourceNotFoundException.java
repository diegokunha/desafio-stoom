package com.deeconsulting.desafiostoom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3142719800417753331L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
