package com.alkemy.ong.service;

import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;

public interface IUserService {
	
	public UserResponse register(UserRegisterRequest UserRegisterRequest);
	
	
}
