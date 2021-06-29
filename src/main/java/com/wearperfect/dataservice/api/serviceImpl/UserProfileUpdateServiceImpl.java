package com.wearperfect.dataservice.api.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.exceptions.AccessForbiddenException;
import com.wearperfect.dataservice.api.exceptions.UserNotFoundException;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;
import com.wearperfect.dataservice.api.security.service.WearperfectUserDetailsService;
import com.wearperfect.dataservice.api.service.FileService;
import com.wearperfect.dataservice.api.service.UserProfileUpdateService;

@Service
public class UserProfileUpdateServiceImpl implements UserProfileUpdateService {

	@Autowired
	WearperfectUserDetailsService wearperfectUserDetailsService;
	
	@Autowired
	FileService fileService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;

	@Autowired
	AmazonS3 amazonS3;

	@Value("${application.aws.s3.buckets.profile-pictures}")
	String profilePicturesS3Bucket;

	@Value("${cloud.aws.region.static}")
	private String postss3Region;

	@Override
	public UserDTO updateProfilePicture(Long userId, MultipartFile profilePicture) {
		WearperfectUserDetails loggedInUser = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (loggedInUser.getUserId().equals(userId)) {
			Optional<User> user = userRepository.findById(loggedInUser.getUserId());
			if (user.isPresent()) {
				Date currentDate = new Date();
				user.get().setLastUpdatedOn(currentDate);
				if (null != profilePicture) {
					File profilePictureFile = fileService.converMultipartFileToFile(profilePicture);
					String fileName = userId+"_"+currentDate.getTime()+ "." + profilePicture.getContentType().substring(profilePicture.getContentType().lastIndexOf("/")+1);
					try {
						if(null != user.get().getProfilePicture()) {
							// Delete existing profile picture
							String existingProfilePictureObjectKey = user.get().getProfilePicture().substring(user.get().getProfilePicture().lastIndexOf("/")+1);
							amazonS3.deleteObject(profilePicturesS3Bucket, existingProfilePictureObjectKey);
						}
						if (profilePicture.getSize() > 1000000) {
							try {
								File scaledImageFile = fileService.resizeImageByPercent(profilePictureFile, fileName, 0.75);
								amazonS3.putObject(profilePicturesS3Bucket, fileName, scaledImageFile);
								if (scaledImageFile.exists()) {
									scaledImageFile.delete();
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							amazonS3.putObject(profilePicturesS3Bucket, fileName, profilePictureFile);
						}
					} finally {
						profilePictureFile.delete();
					}
					user.get().setProfilePicture(
							"https://" + profilePicturesS3Bucket + ".s3." + postss3Region + ".amazonaws.com/" + fileName);
				}
				
				User updateUser = userRepository.saveAndFlush(user.get());
				return userMapper.mapUserToUserDto(updateUser);
			}
			throw new UserNotFoundException();
		}
		throw new AccessForbiddenException(userId, "Cannot update other user's profile picture.");
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
