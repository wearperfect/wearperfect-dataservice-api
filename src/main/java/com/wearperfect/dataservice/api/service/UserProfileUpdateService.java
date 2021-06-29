package com.wearperfect.dataservice.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.UserDTO;

public interface UserProfileUpdateService {

	UserDTO updateProfilePicture(Long userId, MultipartFile profilePicture);

	UserDTO updateProfileCoverPicture(Long userId, MultipartFile profileCoverPicture);

	UserDTO updateFullname(Long userId, String fullname);

	UserDTO updateUsername(Long userId, String username);

	UserDTO updateWebsite(Long userId, String website);

	UserDTO updateBio(Long userId, String bio);

}
