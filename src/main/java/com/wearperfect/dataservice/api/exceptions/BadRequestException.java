package com.wearperfect.dataservice.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class BadRequestException extends HttpClientErrorException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8945948664466432626L;
	
	public BadRequestException() {
		super(HttpStatus.BAD_REQUEST ,"Bad Request. Provided input is not valid.");
	}

	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST ,message);
	}
}
