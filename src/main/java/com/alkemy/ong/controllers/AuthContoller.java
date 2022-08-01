package com.alkemy.ong.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.request.user.UserLoginRequest;
import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;
import com.alkemy.ong.exception.EmailAlreadyExistsException;
import com.alkemy.ong.services.IUserService;

@RestController
@RequestMapping("/auth")
public class AuthContoller {

	@Autowired
	private IUserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRegisterRequest userRegister)
			throws EmailAlreadyExistsException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.register(userRegister));
	}

	@PostMapping("/login")
	public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest userLogin) {
		return ResponseEntity.ok(userService.loginUser(userLogin));
	}
	
}
