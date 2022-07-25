package com.alkemy.ong.dto.response.user;

import com.alkemy.ong.models.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
		private String firstName;
		private String lastName;
		private String email;
		private String photo;
		private List<Role> roles;
}
