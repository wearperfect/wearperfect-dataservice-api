package com.wearperfect.dataservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserService userService;

	@GetMapping(value = "/users")
	public List<UserDTO> getUsers() {
		return userService.getUsers();
	}

	@GetMapping(value = "/users/{userId}")
	public UserDetailsDTO getUserById(@PathVariable(name = "userId") Long userId) {
		return userService.getUserDetailsById(userId);
	}

	@PutMapping(value = "/users/{userId}")
	public UserDTO updateUser(@PathVariable(name = "userId") Long userId,
			@RequestBody(required = true) UserDTO userDto) {
		User user = userMapper.mapUserDtoToUser(userDto);
		return userService.updateUser(user);
	}
	
	@PutMapping(value = "/users/{userId}/profile/basicdetails")
	public UserDTO updateUserBasicProfileDetails(@PathVariable(name = "userId") Long userId,
			@RequestBody(required = true) UserDTO userDto) {
		return userService.updateUserBasicProfileDetails(userId, userDto);
	}
}
