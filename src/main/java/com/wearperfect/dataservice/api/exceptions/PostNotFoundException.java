package com.wearperfect.dataservice.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class PostNotFoundException extends HttpClientErrorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2127283767767853662L;

	public PostNotFoundException() {
		super(HttpStatus.NOT_FOUND, "Post not available.");
	}

	public PostNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

}
