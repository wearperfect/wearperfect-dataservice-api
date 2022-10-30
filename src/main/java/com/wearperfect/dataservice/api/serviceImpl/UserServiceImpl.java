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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearperfect.dataservice.api.dto.AuthenticationRequest;
import com.wearperfect.dataservice.api.dto.BusinessAndSupportDTO;
import com.wearperfect.dataservice.api.dto.CityBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CountryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.PasswordResetDTO;
import com.wearperfect.dataservice.api.dto.StateBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;
import com.wearperfect.dataservice.api.entities.BusinessAndSupport;
import com.wearperfect.dataservice.api.entities.Role;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.BusinessAndSupportMapper;
import com.wearperfect.dataservice.api.mappers.RoleMapper;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repository.BusinessAndSupportRepository;
import com.wearperfect.dataservice.api.repository.FollowRepository;
import com.wearperfect.dataservice.api.repository.PostRepository;
import com.wearperfect.dataservice.api.repository.RoleRepository;
import com.wearperfect.dataservice.api.repository.UserRepository;
import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;
import com.wearperfect.dataservice.api.security.service.JwtUtiilService;
import com.wearperfect.dataservice.api.security.service.WearperfectUserDetailsService;
import com.wearperfect.dataservice.api.service.CityService;
import com.wearperfect.dataservice.api.service.CountryService;
import com.wearperfect.dataservice.api.service.FileService;
import com.wearperfect.dataservice.api.service.FollowService;
import com.wearperfect.dataservice.api.service.StateService;
import com.wearperfect.dataservice.api.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	WearperfectUserDetailsService wearperfectUserDetailsService;

	@Autowired
	JwtUtiilService jwtUtiilService;

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
	CountryService countryService;

	@Autowired
	StateService stateService;

	@Autowired
	CityService cityService;

	@Autowired
	BusinessAndSupportRepository businessAndSupportRepository;

	@Autowired
	BusinessAndSupportMapper businessAndSupportMapper;

	@Autowired
	FileService fileService;

	@Autowired
	FollowService followService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	AmazonS3 amazonS3;

	@Value("${application.aws.s3.buckets.profile-pictures}")
	String profilePicturesS3Bucket;

	@Value("${cloud.aws.region.static}")
	private String postss3Region;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(BCryptVersion.$2Y, 12);

	@Override
	public List<UserDTO> getUsers(String realm) {
		List<User> users;
		if (realm.equalsIgnoreCase("BRANDS")) {
			users = userRepository.findByRoleId(4);
		} else if (realm.equalsIgnoreCase("DESIGNERS")) {
			users = userRepository.findByRoleId(3);
		} else if (realm.equalsIgnoreCase("USERS")) {
			users = userRepository.findByRoleId(2);
		} else {
			users = userRepository.findAll();
		}
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

		WearperfectUserDetails loggedInUser = wearperfectUserDetailsService.getLoggedInUserDetails();

		if (loggedInUser.getUserId() == userId) {
			userDetails.setFollowing(true);
		} else {
			if (followService.isUserFollowedByUserId(userId, loggedInUser.getUserId())) {
				userDetails.setFollowing(true);
			} else {
				userDetails.setFollowing(false);
			}
		}
		userDetails.setTotalPosts(postRepository.countByCreatedBy(userId));
		userDetails.setTotalFollowers(followRepository.countByUserId(userId));
		userDetails.setTotalFollowing(followRepository.countByFollowingBy(userId));
		return userDetails;
	}

	@Override
	public UserDTO createUser(User user) {
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
	public String authenticateUser(AuthenticationRequest authenticationRequest) {
		// BCryptPasswordEncoder passwordEncoder = new
		// BCryptPasswordEncoder(BCryptVersion.$2Y, 12);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException exception) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid credentials");
		}

		final WearperfectUserDetails userDetails = (WearperfectUserDetails) wearperfectUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		return jwtUtiilService.generateToken(userDetails);
	}

	@Override
	public UserDTO updateUserBasicProfileDetails(Long userId, UserDTO userDto, MultipartFile profilePicture) {

		User user = userMapper.mapUserDtoToUser(userDto);

		if (null == user.getId() || !userId.equals(user.getId()) || null == user.getUsername()
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
			} else {
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
		Optional<User> user = userRepository.findByUsername(username);
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

		if (null == user.getId() || !userId.equals(user.getId())) {
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
			UserDetailsDTO updatedUserDto = userMapper.mapUserToUserDetailsDto(existingUserDetails.get());
			if (null != userDto.getCityId()) {
				CityBasicDetailsDTO city = cityService.getCityDetailsByCityId(userDto.getCityId());
				updatedUserDto.setCity(city);
			} else {
				updatedUserDto.setCity(null);
			}
			if (null != userDto.getStateId()) {
				StateBasicDetailsDTO state = stateService.getStateDetailsByStateId(userDto.getStateId());
				updatedUserDto.setState(state);
			} else {
				updatedUserDto.setState(null);
			}
			if (null != userDto.getCountryId()) {
				CountryBasicDetailsDTO country = countryService.getCountryDetailsByCountryId(userDto.getCountryId());
				updatedUserDto.setCountry(country);
			} else {
				updatedUserDto.setCountry(null);
			}
			return updatedUserDto;
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public BusinessAndSupportDTO updateUserBusinessAndSupportDetails(Long userId,
			BusinessAndSupportDTO businessAndSupportDto) {
		BusinessAndSupport businessAndSupport = businessAndSupportMapper
				.mapBusinessAndSupportDtoToBusinessAndSupport(businessAndSupportDto);
		if (null == businessAndSupport.getId()) {

		} else {

		}
		businessAndSupportRepository.save(businessAndSupport);
		return businessAndSupportMapper.mapBusinessAndSupporToBusinessAndSupportDto(businessAndSupport);
	}

	@Override
	public String resetUserPassword(Long userId, PasswordResetDTO passwordResetDto) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			if (passwordResetDto.getNewPassword().equals(passwordResetDto.getConfirmNewPassword())) {
				user.get().setPassword(passwordEncoder.encode(passwordResetDto.getNewPassword()));
				user.get().setPasswordLastUpdatedOn(new Date());
				userRepository.saveAndFlush(user.get());
				return authenticateUser(new AuthenticationRequest(user.get().getUsername(), user.get().getPassword()));
			} else {
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
						"New Password and Confirm New Password values doesn't match.");
			}
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public String changeUserPassword(Long userId, PasswordResetDTO passwordResetDto) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			System.out.println(passwordEncoder.encode(passwordResetDto.getOldPassword()));
			System.out.println(user.get().getPassword());
			if (passwordEncoder.matches(passwordResetDto.getOldPassword(), user.get().getPassword())) {
				if (passwordResetDto.getNewPassword().equals(passwordResetDto.getConfirmNewPassword())) {
					user.get().setPassword(passwordEncoder.encode(passwordResetDto.getNewPassword()));
					user.get().setPasswordLastUpdatedOn(new Date());
					userRepository.saveAndFlush(user.get());
					return authenticateUser(
							new AuthenticationRequest(user.get().getUsername(), passwordResetDto.getNewPassword()));
				} else {
					throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
							"New Password and Confirm New Password values doesn't match.");
				}
			} else {
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Please enter correct Old Password.");
			}
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public UserDetailsDTO changeUserRole(Long userId, Integer roleId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			Optional<Role> role = roleRepository.findById(roleId);
			if (!role.isPresent()) {
				throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE,
						"Invalid role. Please provide valid role.");
			}
			if (!roleId.equals(user.get().getRoleId())) {
				user.get().setRoleId(roleId);
				userRepository.save(user.get());
			}
			UserDetailsDTO userDetails = userMapper.mapUserToUserDetailsDto(user.get());
			userDetails.setRole(roleMapper.mapRoleToRoleBasicDetailsDto(role.get()));
			return userDetails;
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

}
