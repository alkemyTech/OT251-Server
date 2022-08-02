package com.alkemy.ong.controllers;


import java.util.UUID;

import com.alkemy.ong.dto.response.user.UserResponse;
import com.alkemy.ong.services.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.services.IUserService;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserAuthService userServiceAuth;

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("a/users")
	public Page<UserResponse> getAllUsers(Pageable pageable){
		return userServiceAuth.getAllUsers(pageable);
	}

}
