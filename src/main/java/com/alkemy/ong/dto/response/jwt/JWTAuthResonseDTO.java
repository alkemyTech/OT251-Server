package com.alkemy.ong.dto.response.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthResonseDTO {

//	@ApiModelProperty(name = "accessToken", value = "Access Token JWT", dataType = "String")
	private String accessToken;
	
//	@ApiModelProperty(name = "tokenType", value = "Token Type", dataType = "String")
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
