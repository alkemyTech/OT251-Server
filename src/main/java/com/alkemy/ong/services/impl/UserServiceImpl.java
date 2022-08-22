package com.alkemy.ong.services.impl;

import java.util.UUID;

import com.alkemy.ong.dto.request.user.UserUpdateRequest;
import com.alkemy.ong.dto.response.user.UserResponse;
import com.alkemy.ong.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.models.User;
import com.alkemy.ong.repositories.UserRepository;
import com.alkemy.ong.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Override
	public void delete(UUID id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		userRepository.delete(user);
	}

	@Override
	public UserResponse update(UUID id, UserUpdateRequest userDTO) {
		userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		User user = userMapper.userUpdateRequestToUser(userDTO);
		user.setId(id);
		return userMapper.mapResponse(userRepository.save(user));
	}

	@Override
	public Page<UserResponse> getAllUsers(Pageable pageable) {
		Page<User> users = userRepository.findAll(pageable);
		Page<UserResponse> dto = users.map(user -> userMapper.mapResponse(user));
		return dto;
	}
}
