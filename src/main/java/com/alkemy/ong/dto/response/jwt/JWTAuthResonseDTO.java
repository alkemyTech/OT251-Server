package com.alkemy.ong.dto.response.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthResonseDTO {

	private String accessToken;

	public JWTAuthResonseDTO(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

}
