package com.alkemy.ong.config.security.jwt.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.alkemy.ong.config.security.user.UserDetailsImpl;
import com.alkemy.ong.exception.JwtAppException;

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
	private String SECRET_KEY;

	@Value("${app.jwt-expiration-milliseconds}")
	private int JWT_EXPIRATIONINMS;

	public String generateAccessToken(UserDetailsImpl user) {
		Date currentDate = new Date();
		return Jwts.builder().setSubject(String.format(user.getEmail())).setIssuer("ONG251")
				.claim("roles", user.getRoles().toString()).setIssuedAt(new Date())
				.setExpiration(new Date(currentDate.getTime() + JWT_EXPIRATIONINMS))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	public String getUsernameJWT(String token) {
		return parseClaims(token).getSubject();
	}

	public List<String> extractRoles(String token) {
		return (List<String>) Jwts.parser().setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
				.parseClaimsJws(token).getBody().get("roles");
	}

	public Claims parseClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	public boolean validateToken(String token) {

		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			throw new JwtAppException(HttpStatus.BAD_REQUEST, "Invalid JWT Signature");
		} catch (MalformedJwtException e) {
			throw new JwtAppException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
		} catch (ExpiredJwtException e) {
			throw new JwtAppException(HttpStatus.BAD_REQUEST, "Expired JWT Token");
		} catch (UnsupportedJwtException e) {
			throw new JwtAppException(HttpStatus.BAD_REQUEST, "JWT token not supported");
		} catch (IllegalArgumentException e) {
			throw new JwtAppException(HttpStatus.BAD_REQUEST, "The claims JWT string is empty");
		}
	}

}
