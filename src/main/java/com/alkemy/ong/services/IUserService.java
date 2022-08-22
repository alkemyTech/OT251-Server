package com.alkemy.ong.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alkemy.ong.dto.request.user.UserUpdateRequest;
import com.alkemy.ong.dto.response.user.UserResponse;

public interface IUserService {


	void delete(UUID id);
	
	public Page<UserResponse> getAllUsers(Pageable pageable);

	public UserResponse update(UUID id, UserUpdateRequest user);
	
}
