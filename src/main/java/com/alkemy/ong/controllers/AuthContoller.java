package com.alkemy.ong.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.request.jwt.JWTAuthResonseDTO;
import com.alkemy.ong.dto.request.user.UserLoginRequest;
import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;
import com.alkemy.ong.exception.EmailAlreadyExistsException;
import com.alkemy.ong.services.IUserAuthService;

@RestController
@RequestMapping("/auth")
public class AuthContoller {

	@Autowired
	private IUserAuthService userAuthService;

	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRegisterRequest userRegister)
			throws EmailAlreadyExistsException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userAuthService.register(userRegister));
	}

	@PostMapping("/login")
	public ResponseEntity<JWTAuthResonseDTO> login(@RequestBody UserLoginRequest userLogin) {
		return ResponseEntity.ok(userAuthService.loginUser(userLogin));
	}
	
}
