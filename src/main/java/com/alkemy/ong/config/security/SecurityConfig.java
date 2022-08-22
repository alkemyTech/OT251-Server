package com.alkemy.ong.config.security;

import com.amazonaws.services.xray.model.Http;
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

import com.alkemy.ong.config.security.jwt.auth.JwtAuthenticationEntryPoint;
import com.alkemy.ong.config.security.jwt.filter.JwtAuthenticationFilter;
import com.alkemy.ong.config.security.services.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServices;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired 
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	private final String[] swaggerEndpoints ={
			"/swagger-resources/**",
			"/swagger-ui/**", "/v2/api-docs",
			"/v3/api-docs",
			"/api/docs",
			"/api/docs/**",
			"/api/docs/swagger-ui",
			"/**/swagger-ui/**",
			"/swagger-ui"
	};

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf().disable()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				.authorizeRequests()
				/*/activities*/
				.antMatchers(HttpMethod.POST,"/activities").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT,"/activities/{id}").hasRole("ADMIN")
				/*/auth*/
				.antMatchers(HttpMethod.POST, "/auth/register").permitAll()
				.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
				.antMatchers(HttpMethod.POST, "/auth/register").permitAll()
				.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
				/*/categories*/
				.antMatchers(HttpMethod.GET,"/categories/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST,"/categories").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/categories").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT,"/categories/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE,"/categories/{id}").hasRole("ADMIN")
				/*/comments*/
				.antMatchers(HttpMethod.GET, "/comments").permitAll()
				.antMatchers(HttpMethod.POST, "/comments").permitAll()
				/*/contacts*/
				.antMatchers(HttpMethod.GET, "/contacts").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/contacts").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/contacts").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/contacts").permitAll()
				/*/members*/
				.antMatchers(HttpMethod.DELETE, "/members/{id}").hasRole("ADMIN")
				/*/news*/
				.antMatchers(HttpMethod.GET,"/news/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/news").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "news/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "news/{id}").hasRole("ADMIN")
				/*/organization*/
				.antMatchers(HttpMethod.GET, "/organization/public").permitAll()
				.antMatchers(HttpMethod.POST, "/organization/public").hasRole("ADMIN")
				/*/slides*/
				.antMatchers(HttpMethod.GET, "/slides").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/slides/{id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/slides").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/slides/{id}").hasRole("ADMIN")
				/*/testimonials*/
				.antMatchers(HttpMethod.POST,"/testimonials").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET,"/testimonials/get-all").permitAll()
				
				.anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
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
