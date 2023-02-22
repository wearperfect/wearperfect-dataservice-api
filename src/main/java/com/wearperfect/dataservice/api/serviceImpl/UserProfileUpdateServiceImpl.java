package com.wearperfect.dataservice.api.serviceImpl;

import com.amazonaws.services.s3.AmazonS3;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.entity.User;
import com.wearperfect.dataservice.api.exception.AccessForbiddenException;
import com.wearperfect.dataservice.api.exception.UserNotFoundException;
import com.wearperfect.dataservice.api.mapper.UserMapper;
import com.wearperfect.dataservice.api.repository.UserRepository;
import com.wearperfect.dataservice.api.security.models.WearperfectUserPrincipal;
import com.wearperfect.dataservice.api.security.service.WearperfectUserDetailsService;
import com.wearperfect.dataservice.api.service.FileService;
import com.wearperfect.dataservice.api.service.UserProfileUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

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
	
	@Value("${application.aws.s3.buckets.profile-cover-pictures}")
	String profileCoverPicturesS3Bucket;

	@Value("${cloud.aws.region.static}")
	private String postss3Region;

	@Override
	public UserDTO updateProfilePicture(Long userId, MultipartFile profilePicture) {
		WearperfectUserPrincipal loggedInUserDetails = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (loggedInUserDetails.getUserId().equals(userId)) {
			Optional<User> user = userRepository.findById(loggedInUserDetails.getUserId());
			if (user.isPresent()) {
				Date currentDate = new Date();
				user.get().setLastUpdatedOn(currentDate);
				if (null != profilePicture) {
					File profilePictureFile = fileService.converMultipartFileToFile(profilePicture);
					String fileName = "U"+userId+"_PP_"+currentDate.getTime()+ "." + profilePicture.getContentType().substring(profilePicture.getContentType().lastIndexOf("/")+1);
					try {
						if(null != user.get().getProfilePicture()) {
							// Delete existing profile picture
							String existingProfilePictureObjectKey = user.get().getProfilePicture().substring(user.get().getProfilePicture().lastIndexOf("/")+1);
							if(amazonS3.doesObjectExist(profilePicturesS3Bucket, existingProfilePictureObjectKey))
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
		WearperfectUserPrincipal loggedInUserDetails = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (loggedInUserDetails.getUserId().equals(userId)) {
			Optional<User> user = userRepository.findById(loggedInUserDetails.getUserId());
			if (user.isPresent()) {
				Date currentDate = new Date();
				user.get().setLastUpdatedOn(currentDate);
				if (null != profileCoverPicture) {
					File profileCoverPictureFile = fileService.converMultipartFileToFile(profileCoverPicture);
					String fileName = "U"+userId+"_PCP_"+currentDate.getTime()+ "." + profileCoverPicture.getContentType().substring(profileCoverPicture.getContentType().lastIndexOf("/")+1);
					try {
						if(null != user.get().getProfileCoverPicture()) {
							// Delete existing profile picture
							String existingProfileCoverPictureObjectKey = user.get().getProfileCoverPicture().substring(user.get().getProfileCoverPicture().lastIndexOf("/")+1);
							if(amazonS3.doesObjectExist(profileCoverPicturesS3Bucket, existingProfileCoverPictureObjectKey))
								amazonS3.deleteObject(profileCoverPicturesS3Bucket, existingProfileCoverPictureObjectKey);
						}
						if (profileCoverPicture.getSize() > 1000000) {
							try {
								File scaledImageFile = fileService.resizeImageByPercent(profileCoverPictureFile, fileName, 0.75);
								amazonS3.putObject(profileCoverPicturesS3Bucket, fileName, scaledImageFile);
								if (scaledImageFile.exists()) {
									scaledImageFile.delete();
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							amazonS3.putObject(profileCoverPicturesS3Bucket, fileName, profileCoverPictureFile);
						}
					} finally {
						profileCoverPictureFile.delete();
					}
					user.get().setProfileCoverPicture(
							"https://" + profileCoverPicturesS3Bucket + ".s3." + postss3Region + ".amazonaws.com/" + fileName);
				}
				
				User updateUser = userRepository.saveAndFlush(user.get());
				return userMapper.mapUserToUserDto(updateUser);
			}
			throw new UserNotFoundException();
		}
		throw new AccessForbiddenException(userId, "Cannot update other user's profile picture.");
	}

	@Override
	public UserDTO updateFullname(Long userId, String fullname) {
		WearperfectUserPrincipal loggedInUserDetails = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (loggedInUserDetails.getUserId().equals(userId)) {
			Optional<User> user = userRepository.findById(loggedInUserDetails.getUserId());
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
		WearperfectUserPrincipal loggedInUserDetails = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (loggedInUserDetails.getUserId().equals(userId)) {
			Optional<User> user = userRepository.findById(loggedInUserDetails.getUserId());
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
		WearperfectUserPrincipal loggedInUserDetails = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (loggedInUserDetails.getUserId().equals(userId)) {
			Optional<User> user = userRepository.findById(loggedInUserDetails.getUserId());
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
		WearperfectUserPrincipal loggedInUserDetails = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (loggedInUserDetails.getUserId().equals(userId)) {
			Optional<User> user = userRepository.findById(loggedInUserDetails.getUserId());
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
