package com.wearperfect.dataservice.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping("/users")
	public List<UserDTO> getUsers() {
		return userService.getUsers();
	}
	
	@PutMapping("/users")
	public UserDTO updateUser(@RequestBody(required = true) UserDTO userDto) {
		User user = userMapper.mapUserDtoToUse(userDto);
		return userService.updateUser(user);
	}
	
	@GetMapping(value = "/users/{userId}", produces={"application/json","application/xml"})
	@ResponseBody
	public UserDetailsDTO getUserById(@PathVariable(name = "userId") Long userId) {
		return userService.getUserDetailsById(userId);
	}
}
