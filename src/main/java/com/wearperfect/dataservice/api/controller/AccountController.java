package com.wearperfect.dataservice.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearperfect.dataservice.api.cache.service.BlacklistAccessTokenService;
import com.wearperfect.dataservice.api.dto.AccessTokenDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;
import com.wearperfect.dataservice.api.security.models.WearperfectUserPrincipal;
import com.wearperfect.dataservice.api.security.service.WearperfectUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.wearperfect.dataservice.api.dto.AuthenticationRequest;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.service.UserService;

@RestController
public class AccountController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;

	@Autowired
	BlacklistAccessTokenService blacklistAccessTokenService;

	@Autowired
	WearperfectUserDetailsService wearperfectUserDetailsService;
	
	@PostMapping("/signin")
	public String userSignin(@RequestBody(required = true) AuthenticationRequest authenticationRequest) {
		return userService.authenticateUser(authenticationRequest);
	}

	@PostMapping("/login")
	public AccessTokenDTO authenticateUser(@RequestBody(required = true) AuthenticationRequest authenticationRequest) {
		return new AccessTokenDTO(userService.authenticateUser(authenticationRequest));
	}
	
	@PostMapping("/signup")
	public UserDTO registerUser(@RequestBody(required = true) UserDTO userDto) {
		User user = userMapper.mapUserDtoToUser(userDto);
		return userService.createUser(user);
	}

	@PostMapping("/signout")
	public Boolean logoutUser(@RequestHeader(name="Authorization") String bearerToken) {
		return blacklistAccessTokenService.blacklistAccessToken(bearerToken.substring(7));
	}

	@GetMapping("/me")
	public UserDetailsDTO getLoggedInUserDetailsByAuthRequest() throws JsonProcessingException {
		WearperfectUserPrincipal loggedInUserDetails = wearperfectUserDetailsService.getLoggedInUserDetails();
		return userService.getUserDetailsByUsername(loggedInUserDetails.getUsername());
	}
	
}
