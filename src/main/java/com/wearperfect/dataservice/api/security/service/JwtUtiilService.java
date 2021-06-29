package com.wearperfect.dataservice.api.security.service;

import java.util.Date;

import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;

public interface JwtUtiilService {
	
	String generateToken(WearperfectUserDetails userDetails);
	
	String extractUserId(String token);

	Date extractExpiration(String token);

	public Boolean validateToken(String token, WearperfectUserDetails userDetails);
}
