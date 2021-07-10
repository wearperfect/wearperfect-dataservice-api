package com.wearperfect.dataservice.api.security.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;
import com.wearperfect.dataservice.api.security.service.JwtUtiilService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;

@Service
public class JwtUtilServiceImpl implements JwtUtiilService {

	private final String SECRET_KEY = "Wearperfect@2019";

	@Override
	public String generateToken(WearperfectUserDetails userDetails) {
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("userId", userDetails.getUserId());
		claims.put("username", userDetails.getUsername());
		return createToken(claims, String.valueOf(userDetails.getUserId()));
	}
	
	@Override
	public Boolean validateToken(String token, WearperfectUserDetails userDetails) {
		final Long userId = Long.valueOf(extractUserId(token));
		final Date tokenIssuedAt = extractIssuedAt(token);
		final Date tokenExpirationDate = extractExpiration(token);
		Boolean isValidUser = userId.equals(userDetails.getUserId());
		Boolean isPasswordValid = true;
		if(null != userDetails.getPasswordLastUpdatedOn()) {
			isPasswordValid = tokenIssuedAt.getTime()>=userDetails.getPasswordLastUpdatedOn().getTime();
			System.out.println("tokenIssuedAt.getTime():::::::::::::::::::::::::::::::::"+tokenIssuedAt.getTime());
			System.out.println("userDetails.getPasswordLastUpdatedOn().getTime()::::::::"+userDetails.getPasswordLastUpdatedOn().getTime());
		}
		Boolean isTokenNonExpired = new Date().getTime()<tokenExpirationDate.getTime();
		return isValidUser && isPasswordValid && isTokenNonExpired;
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
		return Jwts.builder().addClaims(claims).setSubject(subject).setIssuedAt(new Date()).setExpiration(new Date(3000, 01, 01))
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
