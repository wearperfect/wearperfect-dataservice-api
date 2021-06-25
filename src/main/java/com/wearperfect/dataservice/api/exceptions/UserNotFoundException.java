package com.wearperfect.dataservice.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class UserNotFoundException extends HttpClientErrorException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8945948664466432626L;
	
	public UserNotFoundException() {
		super(HttpStatus.NOT_FOUND ,"user not available.");
	}

	public UserNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND ,message);
	}
}
