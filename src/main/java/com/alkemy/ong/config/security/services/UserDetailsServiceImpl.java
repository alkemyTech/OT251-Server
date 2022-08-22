package com.alkemy.ong.config.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.alkemy.ong.config.security.user.UserDetailsImpl;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.models.User;
import com.alkemy.ong.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	public UserDetailsImpl loadUserByUsername(String userEmail) {
		User user = userRepo.findByEmail(userEmail);
		if (null == user) {
			throw new ResourceNotFoundException("User", "email", userEmail);
		}
		return new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getRoles());
	}
}
