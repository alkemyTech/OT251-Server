package com.alkemy.ong.dto.response.user;

import com.alkemy.ong.models.Role;
import java.util.UUID;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private String photo;
	private List<Role> roles;
}
