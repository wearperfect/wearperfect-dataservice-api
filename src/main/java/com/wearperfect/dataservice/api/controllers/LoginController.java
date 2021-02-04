package com.wearperfect.dataservice.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public UserDTO userSignin(@RequestBody(required = true) UserDTO userDto) {
		User user = userMapper.mapUserDtoToUser(userDto);
		return userService.authenticateUser(user);
	}
	
	@PostMapping("/signup")
	public UserDTO userSignup(@RequestBody(required = true) UserDTO userDto) {
		User user = userMapper.mapUserDtoToUser(userDto);
		return userService.createUser(user);
	}
	
}
