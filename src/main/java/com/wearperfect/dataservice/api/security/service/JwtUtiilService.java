package com.wearperfect.dataservice.api.security.service;

import java.util.Date;

import com.wearperfect.dataservice.api.security.models.CustomUserDetails;

public interface JwtUtiilService {
	
	String generateToken(CustomUserDetails userDetails);
	
	String extractUserId(String token);

	Date extractExpiration(String token);

	public Boolean validateToken(String token, CustomUserDetails userDetails);
}
