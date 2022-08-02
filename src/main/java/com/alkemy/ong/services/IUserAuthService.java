package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.jwt.JWTAuthResonseDTO;
import com.alkemy.ong.dto.request.user.UserLoginRequest;
import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserAuthService {

	UserResponse register(UserRegisterRequest UserRegisterRequest);

	public Page<UserResponse> getAllUsers(Pageable pageable);
	
	JWTAuthResonseDTO loginUser(UserLoginRequest userLogin);


}
