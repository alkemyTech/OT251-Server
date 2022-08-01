package com.alkemy.ong.services.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;
import com.alkemy.ong.exception.EmailAlreadyExistsException;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.UserMapper;
import com.alkemy.ong.models.Role;
import com.alkemy.ong.models.User;
import com.alkemy.ong.repositories.RoleRepository;
import com.alkemy.ong.repositories.UserRepository;
import com.alkemy.ong.services.IMailSenderService;
import com.alkemy.ong.services.IUserService;

@Service
public class UserServiceImp implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IMailSenderService emailService;

	@Autowired
	private RoleRepository roleRepo;


	@Autowired
	private UserMapper userMapper;

	@Override
	public UserResponse register(UserRegisterRequest userRegister) throws EmailAlreadyExistsException {
		if (userRepository.existsByEmail(userRegister.getEmail())) {
			throw new EmailAlreadyExistsException(
					"There is an account with that email adress:" + userRegister.getEmail());
		}
		User user = userMapper.mapModel(userRegister);

		List<Role> roles = new ArrayList();
		Role role = roleRepo.findByName("USER");
		roles.add(role);
		user.setRoles(roles);
		emailService.sendMailRegister(userRegister.getEmail());

		return userMapper.mapResponse(userRepository.save(user));

	}

	@Override
	public void delete(UUID id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		userRepository.delete(user);
	}
	
	@Override
	public UserResponse getUserAuth(String token) {
        token = token.replace("Bearer ", "");
        String email = jwtTokenProvider.GetUsernameJWT(token);
        User userEntity = userRepository.findByEmail(email);
        return userMapper.mapResponse(userEntity);
	}

}