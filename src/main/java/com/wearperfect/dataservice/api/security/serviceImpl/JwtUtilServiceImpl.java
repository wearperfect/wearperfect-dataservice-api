package com.wearperfect.dataservice.api.security.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.security.models.CustomUserDetails;
import com.wearperfect.dataservice.api.security.service.JwtUtiilService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;

@Service
public class JwtUtilServiceImpl implements JwtUtiilService {

	private final String SECRET_KEY = "Wearperfect@2019";

	@Override
	public String generateToken(CustomUserDetails userDetails) {
		HashMap<String, Object> claims = new HashMap<>();
		return createToken(claims, String.valueOf(userDetails.getUserId()));
	}
	
	@Override
	public String extractUserId(String token){
		return extractClaim(token, Claims::getSubject);
	}
	
	@Override
	public Date extractExpiration(String token){
		return extractClaim(token, Claims::getExpiration);
	}
	
	@Override
	public Boolean validateToken(String token, CustomUserDetails userDetails) {
		final Long userId = Long.valueOf(extractUserId(token));
		return (userId == userDetails.getUserId());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().addClaims(claims).setSubject(subject).setIssuedAt(new Date())
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
