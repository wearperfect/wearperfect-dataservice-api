package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.exceptions.AccessForbiddenException;
import com.wearperfect.dataservice.api.exceptions.UserNotFoundException;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;
import com.wearperfect.dataservice.api.security.service.WearperfectUserDetailsService;
import com.wearperfect.dataservice.api.service.UserProfileUpdateService;

@Service
public class UserProfileUpdateServiceImpl implements UserProfileUpdateService {

	@Autowired
	WearperfectUserDetailsService wearperfectUserDetailsService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;

	@Override
	public UserDTO updateProfilePicture(Long userId, MultipartFile profilePicture) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updateProfileCoverPicture(Long userId, MultipartFile profileCoverPicture) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updateFullname(Long userId, String fullname) {
		WearperfectUserDetails loggedInUser = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (loggedInUser.getUserId().equals(userId)) {
			Optional<User> user = userRepository.findById(loggedInUser.getUserId());
			if (user.isPresent()) {
				user.get().setFullname(fullname);
				User updateUser = userRepository.saveAndFlush(user.get());
				return userMapper.mapUserToUserDto(updateUser);
			}
			throw new UserNotFoundException();
		}
		throw new AccessForbiddenException(userId, "Cannot update other user's name.");
	}

	@Override
	public UserDTO updateUsername(Long userId, String username) {
		WearperfectUserDetails loggedInUser = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (loggedInUser.getUserId().equals(userId)) {
			Optional<User> user = userRepository.findById(loggedInUser.getUserId());
			if (user.isPresent()) {
				user.get().setUsername(username);
				User updateUser = userRepository.saveAndFlush(user.get());
				return userMapper.mapUserToUserDto(updateUser);
			}
			throw new UserNotFoundException();
		}
		throw new AccessForbiddenException(userId, "Cannot update other user's username.");
	}

	@Override
	public UserDTO updateWebsite(Long userId, String website) {
		WearperfectUserDetails loggedInUser = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (loggedInUser.getUserId().equals(userId)) {
			Optional<User> user = userRepository.findById(loggedInUser.getUserId());
			if (user.isPresent()) {
				user.get().setWebsite(website);
				User updateUser = userRepository.saveAndFlush(user.get());
				return userMapper.mapUserToUserDto(updateUser);
			}
			throw new UserNotFoundException();
		}
		throw new AccessForbiddenException(userId, "Cannot update other user's website.");
	}

	@Override
	public UserDTO updateBio(Long userId, String bio) {
		WearperfectUserDetails loggedInUser = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (loggedInUser.getUserId().equals(userId)) {
			Optional<User> user = userRepository.findById(loggedInUser.getUserId());
			if (user.isPresent()) {
				user.get().setBio(bio);
				User updateUser = userRepository.saveAndFlush(user.get());
				return userMapper.mapUserToUserDto(updateUser);
			}
			throw new UserNotFoundException();
		}
		throw new AccessForbiddenException(userId, "Cannot update other user's bio");
	}

}
