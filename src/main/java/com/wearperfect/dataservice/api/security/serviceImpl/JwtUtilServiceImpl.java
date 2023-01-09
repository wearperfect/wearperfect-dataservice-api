package com.wearperfect.dataservice.api.security.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.wearperfect.dataservice.api.cache.entities.BlacklistAccessToken;
import com.wearperfect.dataservice.api.cache.service.BlacklistAccessTokenService;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;
import com.wearperfect.dataservice.api.security.service.JwtUtilService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class JwtUtilServiceImpl implements JwtUtilService {

	@Autowired
	BlacklistAccessTokenService blacklistAccessTokenService;

	private final String SECRET_KEY = "Wearperfect@2019";

	@Override
	public String generateToken(WearperfectUserDetails userDetails) {
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("userId", userDetails.getUserId());
		claims.put("username", userDetails.getUsername());
		return createToken(claims, userDetails.getUsername());
	}
	
	@Override
	public Boolean validateTokenIfBlacklisted(String token) {

		final boolean isAccessTokenBlacklisted = blacklistAccessTokenService.verifyIfAccessTokenIsBlacklisted(token);
		if(isAccessTokenBlacklisted){
			System.out.println("JwtUtilServiceImpl:validateToken: AccessToken is blacklisted and is invalid.");
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Access Token is not valid.");
		}

		//ToDo
		// Issue new token on password change and blacklist existing token.

		return true;
	}
	
	@Override
	public String extractSubject(String token){
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractIssuedAt(String token){
		return extractClaim(token, Claims::getIssuedAt);
	}
	
	public Date extractExpiration(String token){
		return extractClaim(token, Claims::getExpiration);
	}
	
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().addClaims(claims).setSubject(subject).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS512, new Base64Codec().encode(SECRET_KEY)).compact();
	}
	
	private <T>T extractClaim(String token, Function<Claims, T> claimsResolver){
		final Claims claims = extractAllClaims(token).getBody();
		return claimsResolver.apply(claims);
	}

	@Override
	public Jws<Claims> extractAllClaims(String token) {
		try{
			//Parsing claims verifies the token internally.
			return Jwts.parser().setSigningKey(new Base64Codec().encode(SECRET_KEY)).parseClaimsJws(token);
		}catch (Exception exception){
			System.out.println(exception.getMessage());
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, exception.getMessage());
		}
	}

}
