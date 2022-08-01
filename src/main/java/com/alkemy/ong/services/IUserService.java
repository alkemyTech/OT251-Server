package com.alkemy.ong.services;


import java.util.UUID;

import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;

public interface IUserService {
	
	public UserResponse register(UserRegisterRequest UserRegisterRequest);

	void delete(UUID id);

	public UserResponse getUserAuth(String token);
	
	
}
