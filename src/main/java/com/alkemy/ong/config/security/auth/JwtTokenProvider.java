package com.alkemy.ong.config.security.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.alkemy.ong.exception.JwtAppException;
import com.alkemy.ong.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;
	
	
	public String generateToken(User user) {
		
		String email = user.getEmail();
		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);
		String token = Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		
		return token;
	}
	
	public String GetUsernameJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}catch (SignatureException e) {
			throw new JwtAppException(HttpStatus.BAD_REQUEST,"Invalid JWT Signature");
		}
		catch (MalformedJwtException e) {
			throw new JwtAppException(HttpStatus.BAD_REQUEST,"Invalid JWT token");
		}
		catch (ExpiredJwtException e) {
			throw new JwtAppException(HttpStatus.BAD_REQUEST,"Expired JWT Token");
		}
		catch (UnsupportedJwtException e) {
			throw new JwtAppException(HttpStatus.BAD_REQUEST,"JWT token not supported");
		}
		catch (IllegalArgumentException e) {
			throw new JwtAppException(HttpStatus.BAD_REQUEST,"The claims JWT string is empty");
		}
	}

}
