package com.alkemy.ong.config.security.filter;

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
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alkemy.ong.config.security.auth.JwtTokenProvider;
import com.alkemy.ong.config.security.services.CustomUserDetailsService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	private static final String AUTHORIZACION_FOR_HEADER = "Authorization";
	private static final String START_WITH_BEARER = "Bearer";
	private static final int BEARER_PART_LENGHT = 7;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getJWTfromRequest(request);

		if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
			String userEmail = jwtTokenProvider.GetUsernameJWT(token);

			UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);
	}

	private String getJWTfromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZACION_FOR_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(START_WITH_BEARER)) {
			return bearerToken.substring(BEARER_PART_LENGHT, bearerToken.length());
		}
		return null;
	}

}
