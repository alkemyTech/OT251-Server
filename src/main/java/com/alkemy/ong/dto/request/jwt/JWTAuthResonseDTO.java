package com.alkemy.ong.dto.request.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthResonseDTO {

	private String accessToken;
	private String tokenType = "Bearer";
	
	public JWTAuthResonseDTO(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public JWTAuthResonseDTO(String accessToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}
	
}
