package com.wearperfect.dataservice.api.security.service;

import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;

public interface JwtUtilService {

	String generateToken(WearperfectUserDetails userDetails);
	
	String extractUserId(String token);

	public Boolean validateToken(String token);
}
