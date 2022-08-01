package com.alkemy.ong.services;

import java.util.UUID;

import com.alkemy.ong.dto.request.user.UserLoginRequest;
import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;

public interface IUserService {

	UserResponse register(UserRegisterRequest UserRegisterRequest);
	
	UserResponse loginUser(UserLoginRequest userLogin);

	void delete(UUID id);

}
