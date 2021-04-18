package com.wearperfect.dataservice.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.AuthenticationRequest;
import com.wearperfect.dataservice.api.dto.AuthenticationResponse;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.service.UserService;

@RestController
public class LoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@PostMapping("/signin")
	public AuthenticationResponse userSignin(@RequestBody(required = true) AuthenticationRequest authenticationRequest) {
		return userService.authenticateUser(authenticationRequest);
	}
	
	@PostMapping("/signup")
	public UserDTO userSignup(@RequestBody(required = true) UserDTO userDto) {
		User user = userMapper.mapUserDtoToUser(userDto);
		return userService.createUser(user);
	}
	
}
