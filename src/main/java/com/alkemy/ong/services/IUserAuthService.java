package com.alkemy.ong.services;

import javax.servlet.http.HttpServletRequest;

import com.alkemy.ong.dto.request.user.UserLoginRequest;
import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.jwt.JWTAuthResonseDTO;
import com.alkemy.ong.dto.response.user.UserAuthenticatedResponse;
import com.alkemy.ong.dto.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserAuthService {

	JWTAuthResonseDTO register(UserRegisterRequest UserRegisterRequest);

	public Page<UserResponse> getAllUsers(Pageable pageable);
	
	JWTAuthResonseDTO loginUser(UserLoginRequest userLogin);
	
	UserAuthenticatedResponse getUserAuth(HttpServletRequest httpServletRequest);

}
