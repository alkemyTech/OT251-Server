package com.alkemy.ong.services;

import java.util.UUID;

import com.alkemy.ong.dto.request.user.UserUpdateRequest;
import com.alkemy.ong.dto.response.user.UserResponse;

public interface IUserService {


	void delete(UUID id);

	public UserResponse update(UUID id, UserUpdateRequest user);
	
}
