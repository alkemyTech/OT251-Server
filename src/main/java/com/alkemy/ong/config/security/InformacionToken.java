package com.alkemy.ong.config.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.alkemy.ong.models.User;
import com.alkemy.ong.services.imp.UserServiceImp;



@Component
public class InformacionToken implements TokenEnhancer{
	
	@Autowired
	private UserServiceImp usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		User usuario = usuarioService.findByEmail(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("informacion", "Acceso: ".concat(authentication.getName()));
		
		info.put("nombre", usuario.getFirstName());
		info.put("apellido", usuario.getLastName());
		info.put("email", usuario.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
