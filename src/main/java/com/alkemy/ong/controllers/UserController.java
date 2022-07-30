package com.alkemy.ong.controllers;


import java.util.UUID;

import com.alkemy.ong.dto.request.user.UserUpdateRequest;
import com.alkemy.ong.dto.response.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.services.IUserService;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	IUserService userService;

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserResponse> editUser(@PathVariable UUID id, @RequestBody UserUpdateRequest user){

		return ResponseEntity.status(HttpStatus.OK).body(userService.update(id,user));
	}
}
