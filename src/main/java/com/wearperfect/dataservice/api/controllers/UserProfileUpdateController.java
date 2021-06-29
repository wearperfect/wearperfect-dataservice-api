package com.wearperfect.dataservice.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.service.UserProfileUpdateService;

@RestController
public class UserProfileUpdateController {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserProfileUpdateService userProfileUpdateService;

	@PutMapping(value = "/users/{userId}/profile/update/profile-picture")
	public UserDTO updateProfilePicture(@PathVariable(name = "userId") Long userId,
			@RequestPart(name = "files", required = false) MultipartFile profilePicture) {
		System.out.println("Profile picture update");
		return userProfileUpdateService.updateProfilePicture(userId, profilePicture);
	}

	@PutMapping(value = "/users/{userId}/profile/update/profile-cover-picture")
	public UserDTO updateProfileCoverPicture(@PathVariable(name = "userId") Long userId,
			@RequestPart(name = "files", required = false) MultipartFile profileCoverPicture) {
		return userProfileUpdateService.updateProfileCoverPicture(userId, profileCoverPicture);
	}

	@PutMapping(value = "/users/{userId}/profile/update/fullname")
	public UserDTO updateFullname(@PathVariable(name = "userId") Long userId,
			@RequestBody(required = true) String fullname) {
		return userProfileUpdateService.updateFullname(userId, fullname);
	}

	@PutMapping(value = "/users/{userId}/profile/update/username")
	public UserDTO updateUsername(@PathVariable(name = "userId") Long userId,
			@RequestBody(required = true) String username) {
		if(null != username && username.trim().length()<3) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Username should have atleast 3 characters.");
		}
		return userProfileUpdateService.updateUsername(userId, username);
	}

	@PutMapping(value = "/users/{userId}/profile/update/website")
	public UserDTO updateWebsite(@PathVariable(name = "userId") Long userId,
			@RequestBody(required = true) String website) {
		return userProfileUpdateService.updateWebsite(userId, website);
	}

	@PutMapping(value = "/users/{userId}/profile/update/bio")
	public UserDTO updateBio(@PathVariable(name = "userId") Long userId, @RequestBody(required = true) String bio) {
		return userProfileUpdateService.updateBio(userId, bio);
	}

}
