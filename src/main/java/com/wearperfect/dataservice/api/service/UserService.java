package com.wearperfect.dataservice.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.BusinessAndSupportDTO;
import com.wearperfect.dataservice.api.dto.BusinessAndSupportDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;
import com.wearperfect.dataservice.api.entities.User;

public interface UserService {

	List<UserDTO> getUsers();
	
	UserDetailsDTO getUserDetailsById(Long userId);

	UserDetailsDTO getUserDetailsByUsername(String username);
	
	UserDTO createUser(User user);

	UserDTO updateUser(User user);

	UserDTO authenticateUser(User user);

	UserDTO updateUserBasicProfileDetails(Long userId, UserDTO userDto, MultipartFile profilePicture);

	UserDetailsDTO updateUserIntroductionDetails(Long userId, UserDTO userDto);

	BusinessAndSupportDetailsDTO updateUserBusinessAndSupportDetails(Long userId, BusinessAndSupportDTO businessAndSupportDto);
	
}
