package com.wearperfect.dataservice.api.security.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.wearperfect.dataservice.api.cache.entities.BlacklistAccessToken;
import com.wearperfect.dataservice.api.cache.service.BlacklistAccessTokenService;
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
		return createToken(claims, String.valueOf(userDetails.getUserId()));
	}
	
	@Override
	public Boolean validateToken(String token) {

		final Date tokenExpirationDate = extractExpiration(token);
		Boolean isTokenNonExpired = new Date().getTime()<tokenExpirationDate.getTime();
		if(!isTokenNonExpired){
			System.out.println("JwtUtilServiceImpl:validateToken: AccessToken is expired and is invalid.");
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Access Token has expired.");
		}

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
	public String extractUserId(String token){
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
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(new Base64Codec().encode(SECRET_KEY)).parseClaimsJws(token).getBody();
	}

}
