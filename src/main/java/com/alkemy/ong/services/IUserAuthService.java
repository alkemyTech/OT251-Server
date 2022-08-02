package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.jwt.JWTAuthResonseDTO;
import com.alkemy.ong.dto.request.user.UserLoginRequest;
import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;

public interface IUserAuthService {

	UserResponse register(UserRegisterRequest UserRegisterRequest);
	
	JWTAuthResonseDTO loginUser(UserLoginRequest userLogin);


}
