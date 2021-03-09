package com.wearperfect.dataservice.api.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearperfect.dataservice.api.dto.BusinessAndSupportDTO;
import com.wearperfect.dataservice.api.dto.BusinessAndSupportDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;
import com.wearperfect.dataservice.api.entities.BusinessAndSupport;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.BusinessAndSupportMapper;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repositories.BusinessAndSupportRepository;
import com.wearperfect.dataservice.api.repositories.FollowRepository;
import com.wearperfect.dataservice.api.repositories.PostRepository;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.service.FileService;
import com.wearperfect.dataservice.api.service.UserService;
import com.wearperfect.dataservice.api.specifications.UserDetailsSpecification;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	FollowRepository followRepository;

	@Autowired
	UserMapper userMapper;

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	BusinessAndSupportRepository businessAndSupportRepository;
	
	@Autowired
	BusinessAndSupportMapper businessAndSupportMapper;

	@Autowired
	FileService fileService;

	@Autowired
	AmazonS3 amazonS3;

	@Value("${application.aws.s3.buckets.profile-pictures}")
	String profilePicturesS3Bucket;

	@Value("${cloud.aws.region.static}")
	private String postss3Region;

	@Override
	public List<UserDTO> getUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> userMapper.mapUserToUserDto(user)).collect(Collectors.toList());
	}

	@Override
	public UserDetailsDTO getUserDetailsById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		UserDetailsDTO userDetails;
		if (user.isPresent()) {
			userDetails = userMapper.mapUserToUserDetailsDto(user.get());
		} else {
			return null;
		}
		userDetails.setTotalPosts(postRepository.countByCreatedBy(userId));
		userDetails.setTotalFollowers(followRepository.countByUserId(userId));
		userDetails.setTotalFollowing(followRepository.countByFollowingBy(userId));
		return userDetails;
	}

	@Override
	public UserDTO createUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(BCryptVersion.$2Y, 12);
		if (null == user.getUsername() || user.getUsername().isEmpty() || null == user.getPassword()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		user.setUsername(user.getUsername().toLowerCase());
		if (null != user.getEmail() && !user.getEmail().isEmpty()) {
			user.setEmail(user.getEmail().toLowerCase());
		}
		// Validate Email
		// Validate Phone number
		// Validate username
		// Validate password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoleId(2); // Default role USER
		user.setCreatedOn(new Date());
		user.setActive(true);

		try {
			System.out.println(">>>>>>>>>" + objectMapper.writeValueAsString(user));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User createdUser = userRepository.saveAndFlush(user);
		return userMapper.mapUserToUserDto(createdUser);
	}

	@Override
	public UserDTO updateUser(User user) {
		// TODO Auto-generated method stub
		if (null == user.getId()) {
			return null;
		}
		// user.setLastUpdatedOn(new Date());
		// User updatedUser = userRepository.saveAndFlush(user);
		// return userMapper.mapUserToUserDto(updatedUser);
		return null;
	}

	@Override
	public UserDTO authenticateUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(BCryptVersion.$2Y, 12);
		List<User> users = userRepository.findAll(
				UserDetailsSpecification.userMobileOrEmailOrUsernamePredicate(user.getUsername().toLowerCase()));
		if (users.size() == 1 && passwordEncoder.matches(user.getPassword(), users.get(0).getPassword())) {
			return userMapper.mapUserToUserDto(users.get(0));
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public UserDTO updateUserBasicProfileDetails(Long userId, UserDTO userDto, MultipartFile profilePicture) {

		User user = userMapper.mapUserDtoToUser(userDto);

		if (null == user.getId() || userId != user.getId() || null == user.getUsername()
				|| user.getUsername().trim().length() <= 0 || null == user.getFullname()
				|| user.getFullname().trim().length() <= 0) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		Optional<User> existingUserDetails = userRepository.findById(userId);

		if (existingUserDetails.isPresent()) {
			existingUserDetails.get().setUsername(user.getUsername());
			existingUserDetails.get().setFullname(user.getFullname());
			existingUserDetails.get().setBio(user.getBio());
			if (null == user.getWebsite() || user.getWebsite().trim().length() <= 0) {
				existingUserDetails.get().setWebsite(null);// Validate url
			}else {
				existingUserDetails.get().setWebsite(user.getWebsite());
			}
			existingUserDetails.get().setLastUpdatedOn(new Date());

			if (null != profilePicture) {
				File profilePictureFile = fileService.converMultipartFileToFile(profilePicture);
				String fileName = userId + "." + fileService.getFileExtension(profilePicture.getOriginalFilename());
				try {
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
				existingUserDetails.get().setProfilePicture(
						"https://" + profilePicturesS3Bucket + ".s3." + postss3Region + ".amazonaws.com/" + fileName);
			}

			userRepository.saveAndFlush(existingUserDetails.get());
			return userMapper.mapUserToUserDto(existingUserDetails.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public UserDetailsDTO getUserDetailsByUsername(String username) {
		Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
		UserDetailsDTO userDetails;
		if (user.isPresent()) {
			userDetails = userMapper.mapUserToUserDetailsDto(user.get());
		} else {
			return null;
		}
		userDetails.setTotalPosts(postRepository.countByCreatedBy(user.get().getId()));
		userDetails.setTotalFollowers(followRepository.countByUserId(user.get().getId()));
		userDetails.setTotalFollowing(followRepository.countByFollowingBy(user.get().getId()));
		return userDetails;
	}

	@Override
	public UserDetailsDTO updateUserIntroductionDetails(Long userId, UserDTO userDto) {
		User user = userMapper.mapUserDtoToUser(userDto);

		if (null == user.getId() || userId != user.getId()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		Optional<User> existingUserDetails = userRepository.findById(userId);

		if (existingUserDetails.isPresent()) {
			existingUserDetails.get().setDob(user.getDob());
			existingUserDetails.get().setCityId(user.getCityId());
			existingUserDetails.get().setStateId(user.getStateId());
			existingUserDetails.get().setCountryId(user.getCountryId());
			existingUserDetails.get().setLastUpdatedOn(new Date());
			userRepository.saveAndFlush(existingUserDetails.get());
			return userMapper.mapUserToUserDetailsDto(existingUserDetails.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public BusinessAndSupportDetailsDTO updateUserBusinessAndSupportDetails(Long userId,
			BusinessAndSupportDTO businessAndSupportDto) {
		BusinessAndSupport businessAndSupport = businessAndSupportMapper.mapBusinessAndSupportDtoToBusinessAndSupport(businessAndSupportDto);
		if(null == businessAndSupport.getId()) {
			
		}else {
			
		}
		businessAndSupportRepository.save(businessAndSupport);
		return businessAndSupportMapper.mapBusinessAndSupportToBusinessAndSupportDetailsDto(businessAndSupport);
	}


}
