package com.alkemy.ong.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.user.UserResponse;
import com.alkemy.ong.exception.EmailAlreadyExistsException;
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

	//rama 25 Erika Brito
	//@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByEmail(String email) throws Exception {
		User usuario = userRepository.findByEmail(email);

		if(usuario == null){
			throw new Exception("No se encontró el email:" + email);		
		}

		boolean estado;
		if (usuario.getDeleted()) //el parametro es enabled, traduzco desde deleted
			estado = false;
		else estado = true;

		List<GrantedAuthority> authorities = usuario.getRoles()
			.stream()
			.map(Role -> new SimpleGrantedAuthority(Role.getName()))
			.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(), estado, true, true, true, authorities);
	}

}
