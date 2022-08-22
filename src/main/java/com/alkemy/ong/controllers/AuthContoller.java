package com.alkemy.ong.controllers;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.alkemy.ong.dto.response.error.ErrorDetailsResponse;
import com.alkemy.ong.dto.response.jwt.JWTAuthResonseDTO;
import com.alkemy.ong.dto.response.user.UserAuthenticatedResponse;
import com.alkemy.ong.exception.EmailAlreadyExistsException;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.services.IUserAuthService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/auth")
public class AuthContoller {

	@Autowired
	private IUserAuthService userAuthService;

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = "Method to register a user", notes = "Return a JWT and store a new user in the database", produces="application/json",consumes="application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 203, message = "ACCEPTED - User register successfully", response = JWTAuthResonseDTO.class),
			@ApiResponse(code = 400, message = "BAD_REQUEST - Param invalid. ", response = IllegalArgumentException.class),
			@ApiResponse(code = 409, message = "CONFLICT - There is an account with that email adress. ", response = EmailAlreadyExistsException.class) })
	@PostMapping("/register")
	public ResponseEntity<JWTAuthResonseDTO> register(@RequestBody @Valid UserRegisterRequest userRegister)
			throws EmailAlreadyExistsException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userAuthService.register(userRegister));
	}

	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Login with email and password", notes = "Return a JWT and login on the API", produces = "application/json", consumes = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "OK - User authenticated", response = JWTAuthResonseDTO.class),
	        @ApiResponse(code = 401, message = "UNAUTHORIZED - Bad credentials", response = AuthenticationException.class),
			@ApiResponse(code = 404, message = "NOT_FOUND - User not found", response = ResourceNotFoundException.class) })
	@PostMapping("/login")
	public ResponseEntity<JWTAuthResonseDTO> login(@RequestBody UserLoginRequest userLogin) {
		return ResponseEntity.ok(userAuthService.loginUser(userLogin));
	}

	@ResponseStatus(HttpStatus.OK)
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Get my user details", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK - User Details", response = UserAuthenticatedResponse.class),
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = ErrorDetailsResponse.class),
			@ApiResponse(code = 404, message = "NOT_FOUND - User not found", response = ResourceNotFoundException.class) })
	@GetMapping("/me")
	public ResponseEntity<UserAuthenticatedResponse> getUser(HttpServletRequest httpServletRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(userAuthService.getUserAuth(httpServletRequest));
	}
}
