package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.*;
import com.wearperfect.dataservice.api.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    Logger LOGGER = LoggerFactory.getLogger(UserService.class);

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
