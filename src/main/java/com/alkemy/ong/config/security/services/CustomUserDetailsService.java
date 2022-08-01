package com.alkemy.ong.config.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.alkemy.ong.models.User;
import com.alkemy.ong.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String userEmail)  {
		User user = userRepo.findByEmail(userEmail);
		if (null == user) {
			throw new ResourceNotFoundException("the mail is not found", "email", userEmail);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null/*mappRoles(user.getRoles())*/);
	}
}
