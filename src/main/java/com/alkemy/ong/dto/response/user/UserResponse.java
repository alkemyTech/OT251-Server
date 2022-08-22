package com.alkemy.ong.dto.response.user;

import java.util.Set;
import java.util.UUID;

import com.alkemy.ong.models.Role;

import lombok.Data;

@Data
public class UserResponse {
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private String photo;
	private Set<Role> roles;
}
