package com.wearperfect.dataservice.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wearperfect.dataservice.api.cache.service.BlacklistAccessTokenService;
import com.wearperfect.dataservice.api.dto.AccessTokenDTO;
import com.wearperfect.dataservice.api.dto.AuthenticationRequest;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;
import com.wearperfect.dataservice.api.entity.User;
import com.wearperfect.dataservice.api.mapper.UserMapper;
import com.wearperfect.dataservice.api.security.models.WearperfectUserPrincipal;
import com.wearperfect.dataservice.api.security.service.WearperfectUserDetailsService;
import com.wearperfect.dataservice.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/v1/signin")
    public String userSignIn(@RequestBody(required = true) AuthenticationRequest authenticationRequest) {
        return userService.authenticateUser(authenticationRequest);
    }

    @PostMapping("/v1/login")
    public AccessTokenDTO authenticateUser(@RequestBody(required = true) AuthenticationRequest authenticationRequest) {
        return new AccessTokenDTO(userService.authenticateUser(authenticationRequest));
    }

    @PostMapping("/v1/signup")
    public UserDTO registerUser(@RequestBody(required = true) UserDTO userDto) {
        User user = userMapper.mapUserDtoToUser(userDto);
        return userService.createUser(user);
    }

    @PostMapping("/v1/signout")
    public Boolean logoutUser(@RequestHeader(name = "Authorization") String bearerToken) {
        return blacklistAccessTokenService.blacklistAccessToken(bearerToken.substring(7));
    }

    @GetMapping("/v1/me")
    public UserDetailsDTO getLoggedInUserDetailsByAuthRequest() throws JsonProcessingException {
        WearperfectUserPrincipal loggedInUserDetails = wearperfectUserDetailsService.getLoggedInUserDetails();
        return userService.getUserDetailsByUsername(loggedInUserDetails.getUsername());
    }

}
