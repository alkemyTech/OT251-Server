//package com.alkemy.ong.config.security;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import com.alkemy.ong.exception.JwtAppException;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtTokenProvider {
//
//	@Value("${app.jwt-secret}")
//	private String jwtSecret;
//	
//	@Value("${app.jwt-expiration-milliseconds}")
//	private int jwtExpirationInMs;
//	
//	
//	public String generateToken(Authentication authentication) {
//		
//		String email = authentication.getName();
//		Date currentDate = new Date();
//		Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);
//		String token = Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(expirationDate)
//				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
//		
//		return token;
//	}
//	
//	public String GetUsernameJWT(String token) {
//		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//		return claims.getSubject();
//	}
//	
//	public boolean validateToken(String token) {
//		try {
//			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//			return true;
//		}catch (MalformedJwtException ex) {
//			throw new JwtAppException(HttpStatus.BAD_REQUEST,"JWT token not valid");
//		}
//		catch (ExpiredJwtException ex) {
//			throw new JwtAppException(HttpStatus.BAD_REQUEST,"Expired JWT Token");
//		}
//	}
//	
//}
