package com.alkemy.ong.services.impl;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alkemy.ong.config.security.jwt.utils.JwtTokenProvider;
import com.alkemy.ong.config.security.user.UserDetailsImpl;
import com.alkemy.ong.dto.request.user.UserLoginRequest;
import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.jwt.JWTAuthResonseDTO;
import com.alkemy.ong.dto.response.user.UserAuthenticatedResponse;
import com.alkemy.ong.dto.response.user.UserResponse;
import com.alkemy.ong.exception.EmailAlreadyExistsException;
import com.alkemy.ong.mappers.UserMapper;
import com.alkemy.ong.models.Role;
import com.alkemy.ong.models.User;
import com.alkemy.ong.repositories.RoleRepository;
import com.alkemy.ong.repositories.UserRepository;
import com.alkemy.ong.services.IMailSenderService;
import com.alkemy.ong.services.IUserAuthService;

@Service
public class UserAuthServiceImpl implements IUserAuthService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private IMailSenderService emailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public JWTAuthResonseDTO register(UserRegisterRequest userRegister) {
		if (userRepository.existsByEmail(userRegister.getEmail())) {
			throw new EmailAlreadyExistsException(
					"There is an account with that email adress:" + userRegister.getEmail());
		}
		User user = userMapper.mapModel(userRegister);

		Set<Role> roles = new HashSet<>();
		Role role = roleRepository.findByName("ROLE_ADMIN");
		roles.add(role);
		user.setRoles(roles);

		userRepository.save(user);

		String token = jwtTokenProvider
				.generateAccessToken(new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getRoles()));
		emailService.sendMailRegister(userRegister.getEmail());

		return new JWTAuthResonseDTO(token);
	}

	@Override
	public JWTAuthResonseDTO loginUser(UserLoginRequest userLogin) {
		Authentication auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		User user = userRepository.findByEmail(auth.getName());
		String token = jwtTokenProvider.generateAccessToken(
				new UserDetailsImpl(user.getEmail(), user.getPassword(), (Set<Role>) user.getRoles()));
		return new JWTAuthResonseDTO(token);
	}

	@Override
	public UserAuthenticatedResponse getUserAuth(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization").substring(7);
		User user = userRepository.findByEmail(jwtTokenProvider.getUsernameJWT(token));
		return userMapper.mapResponseAuthenticate(user);
	}

}
