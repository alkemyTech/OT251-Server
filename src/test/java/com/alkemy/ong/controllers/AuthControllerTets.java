package com.alkemy.ong.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.alkemy.ong.services.IUserAuthService;

@WebMvcTest(AuthContoller.class)
public class AuthControllerTets {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IUserAuthService UserAuthService;
	
	@Test
	void testVerDetalles() {
		
	}
}
