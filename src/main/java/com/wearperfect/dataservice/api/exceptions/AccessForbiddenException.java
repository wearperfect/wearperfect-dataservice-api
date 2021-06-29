package com.wearperfect.dataservice.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class AccessForbiddenException extends HttpClientErrorException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8945948664466432626L;
	
	public AccessForbiddenException() {
		super(HttpStatus.FORBIDDEN ,"User is not allowed to perform this operation.");
	}
	
	public AccessForbiddenException(Long userId) {
		super(HttpStatus.FORBIDDEN ,"User "+userId+" is not allowed to perform this operation.");
	}

	public AccessForbiddenException(String message) {
		super(HttpStatus.FORBIDDEN ,message);
	}
	
	public AccessForbiddenException(Long userId, String message) {
		super(HttpStatus.FORBIDDEN ,"User "+userId+" is not allowed to perform this operation. "+message);
	}
}