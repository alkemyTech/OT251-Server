package com.alkemy.ong.services.imp;

import java.util.UUID;

import com.alkemy.ong.dto.request.user.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;
import com.alkemy.ong.exception.EmailAlreadyExistsException;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.UserMapper;
import com.alkemy.ong.models.User;
import com.alkemy.ong.repositories.UserRepository;
import com.alkemy.ong.services.IUserService;

@Service
public class UserServiceImp implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserResponse register(UserRegisterRequest userRegister) throws EmailAlreadyExistsException {
		if (userRepository.existsByEmail(userRegister.getEmail())) {
			throw new EmailAlreadyExistsException(
					"There is an account with that email adress:" + userRegister.getEmail());
		}
		User user = userMapper.mapModel(userRegister);
//		RolEntity roles = rolRepo.findByName("ROLE").get();
//		user.setRoles(Collections.singleton(roles));
		return userMapper.mapResponse(userRepository.save(user));

	}

	@Override
	public void delete(UUID id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		userRepository.delete(user);
	}

	@Override
	public UserResponse update(UUID id, UserUpdateRequest userDTO) {
		userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		User user = userMapper.userUpdateRequestToUser(userDTO);
		user.setId(id);
		return userMapper.mapResponse(userRepository.save(user));
	}

}
