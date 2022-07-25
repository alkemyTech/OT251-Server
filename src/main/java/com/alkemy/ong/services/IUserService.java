package com.alkemy.ong.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;
import com.alkemy.ong.models.User;

public interface IUserService {
	
	public UserResponse register(UserRegisterRequest UserRegisterRequest);
	
	//rama 25 Brito Erika
	public UserDetails loadUserByEmail(String email);
	public User findByEmail(String email);
}
