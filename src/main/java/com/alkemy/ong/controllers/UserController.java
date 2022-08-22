package com.alkemy.ong.controllers;

import java.util.UUID;

import com.alkemy.ong.services.IUserAuthService;
import com.alkemy.ong.dto.request.user.UserUpdateRequest;
import com.alkemy.ong.dto.response.user.UserResponse;
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

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public Page<UserResponse> getAllUsers(Pageable pageable) {
		return userService.getAllUsers(pageable);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserResponse> editUser(@PathVariable UUID id, @RequestBody UserUpdateRequest user) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, user));
	}
}
