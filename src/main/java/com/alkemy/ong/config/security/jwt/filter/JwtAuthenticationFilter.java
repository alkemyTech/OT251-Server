package com.alkemy.ong.config.security.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alkemy.ong.config.security.jwt.utils.JwtTokenProvider;
import com.alkemy.ong.config.security.user.UserDetailsImpl;
import com.alkemy.ong.models.Role;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	private static final String AUTHORIZACION_FOR_HEADER = "Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!hasAuthorizationBearer(request)) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = getAccessToken(request);

		if (!jwtTokenProvider.validateToken(token)) {
			filterChain.doFilter(request, response);
			return;
		}

		setAuthenticationContext(token, request);
		filterChain.doFilter(request, response);
	}

	private boolean hasAuthorizationBearer(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZACION_FOR_HEADER);
		if (ObjectUtils.isEmpty(bearerToken) || !bearerToken.startsWith("Bearer")) {
			return false;
		}
		return true;
	}

	private String getAccessToken(HttpServletRequest request) {
		String header = request.getHeader(AUTHORIZACION_FOR_HEADER);
		String token = header.split(" ")[1].trim();
		return token;
	}

	private void setAuthenticationContext(String token, HttpServletRequest request) {
		UserDetailsImpl userDetails = getUserDetails(token);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());

		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private UserDetailsImpl getUserDetails(String token) {
		UserDetailsImpl userDetails = new UserDetailsImpl();
		Claims claims = jwtTokenProvider.parseClaims(token);
		String subject = (String) claims.get(Claims.SUBJECT);
		String roles = (String) claims.get("roles");

		roles = roles.replace("[", "").replace("]", "").replace(" ", "");
		String[] roleNames = roles.split(",");

		for (String aRoleName : roleNames) {
			userDetails.addRole(new Role(aRoleName));
		}

		String[] jwtSubject = subject.split(",");

		userDetails.setEmail(jwtSubject[0]);

		return userDetails;
	}

}
