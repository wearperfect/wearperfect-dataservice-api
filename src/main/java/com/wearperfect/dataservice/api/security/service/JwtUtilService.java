package com.wearperfect.dataservice.api.security.service;

import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface JwtUtilService {

	String generateToken(WearperfectUserDetails userDetails);
	
	String extractSubject(String token);

	Jws<Claims> extractAllClaims(String token);

	public Boolean validateTokenIfBlacklisted(String token);
}
