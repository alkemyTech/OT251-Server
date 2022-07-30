package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.request.user.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;
import com.alkemy.ong.models.User;

@Component
public class UserMapper {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserResponse mapResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setEmail(user.getEmail());
		userResponse.setPhoto(user.getPhoto());
		userResponse.setRoles(user.getRoles());
		return userResponse;
	}

	public User mapModel(UserRegisterRequest userRequest) {
		User userRegisterRequest = new User();
		userRegisterRequest.setFirstName(userRequest.getFirstName());
		userRegisterRequest.setLastName(userRequest.getLastName());
		userRegisterRequest.setEmail(userRequest.getEmail());
		userRegisterRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		return userRegisterRequest;
	}

	public User userUpdateRequestToUser(UserUpdateRequest userUpdateRequest) {
		User user = new User();
		user.setFirstName(userUpdateRequest.getFirstName());
		user.setLastName(userUpdateRequest.getLastName());
		user.setEmail(userUpdateRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
		user.setPhoto(userUpdateRequest.getPhoto());
		user.setRoles(userUpdateRequest.getRoles());
		return user;
	}


}
