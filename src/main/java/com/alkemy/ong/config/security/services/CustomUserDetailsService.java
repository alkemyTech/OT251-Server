package com.alkemy.ong.config.security.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.models.Role;
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
			throw new ResourceNotFoundException("User", "email", userEmail);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRoles(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRoles(List<Role> roles){
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
	}
}
