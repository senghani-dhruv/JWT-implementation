package com.jwtimplementation.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	 private static String secretKey = "YUhSMGNITTZMeTkzWldKaGRHVXRhRzkzZEM1amIyMGlhWE1pTkdjdE9TNWpiMjA9"; // Correct Base64 encoded key 
	 
	 private static long jwtExpireTime = 360000 * 6;
	 
	 public long getExpirationTime() {
		 return jwtExpireTime;
	 }	
	 
	 public static String extractUsername(String token) {
		 return extractClaims(token , Claims::getSubject);
	 }
	 
	 public static <T> T extractClaims(String token,Function<Claims,T> claimsResolver) {
		 final Claims claims = extractAllClaims(token);
		 return claimsResolver.apply(claims);
	 }
	 
	 private static Claims extractAllClaims(String token) {
		 return Jwts.parserBuilder()
				 .setSigningKey(getSignKey())
				 .build()
				 .parseClaimsJws(token)
				 .getBody();
	 }
	 
	 //generate token with extra claims
	 public String generateToken(Map<String, Object> extraClims,UserDetails userDetails) {
		 return buildToken(extraClims,userDetails,jwtExpireTime);
	 }
	 
	 public String buildToken(Map<String, Object> extraClims,UserDetails userDetails,long jwtExpireTime) {
		 return Jwts.builder()
				 .setClaims(extraClims)
				 .setSubject(userDetails.getUsername())
				 .setIssuedAt(new Date(System.currentTimeMillis()))
				 .setExpiration(new Date(System.currentTimeMillis() + jwtExpireTime))
				 .signWith(getSignKey(),SignatureAlgorithm.HS256)
				 .compact();
	 }
	 
	 public static Key getSignKey() {
		 byte[] keyByte = Decoders.BASE64.decode(secretKey);
		 return Keys.hmacShaKeyFor(keyByte);
	 }
	 
	 //check it the token is valid or not
	 public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUsername(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	 }
	 
	 private boolean isTokenExpired(String token) {
		 return extractExpiration(token).before(new Date());
	 }
	 
	 private Date extractExpiration(String token) {
		 return extractClaims(token, Claims::getExpiration);
	 }
}
