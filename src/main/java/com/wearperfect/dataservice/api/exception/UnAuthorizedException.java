package com.wearperfect.dataservice.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.Serial;

public class UnAuthorizedException extends HttpClientErrorException{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 8945948664466432626L;
	
	public UnAuthorizedException() {
		super(HttpStatus.UNAUTHORIZED ,"User is not authorized to perform this operation.");
	}

	public UnAuthorizedException(String message) {
		super(HttpStatus.UNAUTHORIZED ,message);
	}
}