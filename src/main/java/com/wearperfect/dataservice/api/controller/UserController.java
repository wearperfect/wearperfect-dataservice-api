package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.BusinessAndSupportDTO;
import com.wearperfect.dataservice.api.dto.PasswordResetDTO;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @GetMapping(value = "/v1/users")
    public List<UserDTO> getUsers(@RequestParam(name = "realm") String realm) {
        return userService.getUsers(realm);
    }

    @GetMapping(value = "/v1/users/{userId}")
    public UserDetailsDTO getUserById(@PathVariable(name = "userId") Long userId) {
        return userService.getUserDetailsById(userId);
    }

    @GetMapping(value = "/v1/users/u/{username}")
    public UserDetailsDTO getUserByUsername(@PathVariable(name = "username") String username) {
        return userService.getUserDetailsByUsername(username);
    }

    @PutMapping(value = "/v1/users/{userId}")
    public UserDTO updateUser(@PathVariable(name = "userId") Long userId, @RequestBody(required = true) UserDTO userDto) {
        User user = userMapper.mapUserDtoToUser(userDto);
        return userService.updateUser(user);
    }

    @PutMapping(value = "/v1/users/{userId}/password/reset")
    public String resetUserPassword(@PathVariable(name = "userId") Long userId, @RequestBody(required = true) PasswordResetDTO passwordResetDto) {
        return userService.resetUserPassword(userId, passwordResetDto);
    }

    @PutMapping(value = "/v1/users/{userId}/password/change")
    public String changeUserPassword(@PathVariable(name = "userId") Long userId, @RequestBody(required = true) PasswordResetDTO passwordResetDto) {
        return userService.changeUserPassword(userId, passwordResetDto);
    }

    @PutMapping(value = "/v1/users/{userId}/role/switch/to/{roleId}")
    public UserDetailsDTO changeUserRole(@PathVariable(name = "userId") Long userId, @PathVariable(name = "roleId") Integer roleId) {
        return userService.changeUserRole(userId, roleId);
    }

    @PutMapping(value = "/v1/users/{userId}/profile/basicdetails")
    public UserDTO updateUserBasicProfileDetails(@PathVariable(name = "userId") Long userId, @RequestPart(name = "data", required = true) UserDTO userDto, @RequestPart(name = "files", required = false) MultipartFile profilePicture) {
        return userService.updateUserBasicProfileDetails(userId, userDto, profilePicture);
    }

    @PutMapping(value = "/v1/users/{userId}/profile/introdetails")
    public UserDetailsDTO updateUserIntroductionDetails(@PathVariable(name = "userId") Long userId, @RequestBody(required = true) UserDTO userDto) {
        return userService.updateUserIntroductionDetails(userId, userDto);
    }

    @PutMapping(value = "/v1/users/{userId}/profile/businessdetails")
    public BusinessAndSupportDTO updateUserBusinessAndSupportDetails(@PathVariable(name = "userId") Long userId, @RequestBody(required = true) BusinessAndSupportDTO businessAndSupportDto) {
        return userService.updateUserBusinessAndSupportDetails(userId, businessAndSupportDto);
    }
}
