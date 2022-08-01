package com.alkemy.ong.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.request.user.UserLoginRequest;
import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;
import com.alkemy.ong.exception.EmailAlreadyExistsException;
import com.alkemy.ong.mappers.UserMapper;
import com.alkemy.ong.models.User;
import com.alkemy.ong.repositories.UserRepository;
import com.alkemy.ong.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private AuthenticationManager authenticationManager;
	
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
	public UserResponse loginUser(UserLoginRequest userLogin) {
		Authentication auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		User user = userRepository.findByEmail(userLogin.getEmail());
		return userMapper.mapResponse(user);
	}

}
