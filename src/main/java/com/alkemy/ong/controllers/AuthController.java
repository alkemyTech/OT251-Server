package com.alkemy.ong.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.request.user.UserLoginRequest;
import com.alkemy.ong.dto.request.user.UserRegisterRequest;
import com.alkemy.ong.dto.response.jwt.JWTAuthResonseDTO;
import com.alkemy.ong.dto.response.user.UserAuthenticatedResponse;
import com.alkemy.ong.exception.EmailAlreadyExistsException;
import com.alkemy.ong.services.IUserAuthService;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication controller")
public class AuthController {

	@Autowired
	private IUserAuthService userAuthService;

	@ResponseStatus(HttpStatus.ACCEPTED)
	@Operation(summary = "Method to register a user", description = "Return a JWT and store a new user in the database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "203", description = "ACCEPTED - User register successfully"),
			@ApiResponse(responseCode = "400", description = "BAD_REQUEST - Param invalid. "),
			@ApiResponse(responseCode = "409", description = "CONFLICT - There is an account with that email adress. ")})
	@PostMapping("/register")
	public ResponseEntity<JWTAuthResonseDTO> register(@RequestBody @Valid UserRegisterRequest userRegister)
			throws EmailAlreadyExistsException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userAuthService.register(userRegister));
	}

	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Login with email and password", description = "Return a JWT and login on the API")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "OK - User authenticated"),
	        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - Bad credentials"),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND - User not found") })
	@PostMapping("/login")
	public ResponseEntity<JWTAuthResonseDTO> login(@RequestBody UserLoginRequest userLogin) {
		return ResponseEntity.ok(userAuthService.loginUser(userLogin));
	}

	@ResponseStatus(HttpStatus.OK)
	@SecurityRequirement(name = "Bearer Authentication")
	@Operation(summary = "Get my user details", description = "application/json")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK - User Details"),
			@ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
			@ApiResponse(responseCode = "404", description = "NOT_FOUND - User not found") })
	@GetMapping("/me")
	public ResponseEntity<UserAuthenticatedResponse> getUser(HttpServletRequest httpServletRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(userAuthService.getUserAuth(httpServletRequest));
	}
}
