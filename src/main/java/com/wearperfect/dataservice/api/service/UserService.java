package com.wearperfect.dataservice.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.AuthenticationRequest;
import com.wearperfect.dataservice.api.dto.BusinessAndSupportDTO;
import com.wearperfect.dataservice.api.dto.PasswordResetDTO;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.dto.UserProfileDTO;
import com.wearperfect.dataservice.api.entity.User;

public interface UserService {

	List<UserDTO> getUsers(String realm);
	
	UserProfileDTO getUserDetailsById(Long userId);

	UserProfileDTO getUserDetailsByUsername(String username);
	
	UserDTO createUser(User user);

	UserDTO updateUser(User user);

	String authenticateUser(AuthenticationRequest authenticationRequest);

	UserDTO updateUserBasicProfileDetails(Long userId, UserDTO userDto, MultipartFile profilePicture);

	UserProfileDTO updateUserIntroductionDetails(Long userId, UserDTO userDto);

	BusinessAndSupportDTO updateUserBusinessAndSupportDetails(Long userId, BusinessAndSupportDTO businessAndSupportDto);

	String resetUserPassword(Long userId, PasswordResetDTO passwordResetDto);

	String changeUserPassword(Long userId, PasswordResetDTO passwordResetDto);
	
	UserProfileDTO changeUserRole(Long userId, Integer roleId);
	
}
