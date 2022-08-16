package com.alkemy.ong.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alkemy.ong.config.security.auth.JwtAuthenticationEntryPoint;
import com.alkemy.ong.config.security.filter.JwtAuthenticationFilter;
import com.alkemy.ong.config.security.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsServices;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf().disable()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/comments").permitAll()
				.antMatchers(HttpMethod.POST, "/comments").permitAll()
				.antMatchers(HttpMethod.POST, "/auth/register").permitAll()
				.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
				.antMatchers(HttpMethod.POST, "/contacts").permitAll()
				.antMatchers(HttpMethod.GET, "/organization/public").permitAll()
				.antMatchers(HttpMethod.POST, "/organization/public").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/contacts").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/slides").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/slides").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/slides/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/slides/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET,"/categories/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST,"/categories").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT,"/categories/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE,"/categories/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/news").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET,"/news/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "news/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "news/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST,"/activities").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT,"/activities/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/members/{id}").hasRole("ADMIN")
				.anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServices).passwordEncoder(encoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
