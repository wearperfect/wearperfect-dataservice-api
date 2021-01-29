package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.AddressDTO;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;
import com.wearperfect.dataservice.api.entities.User;

public interface UserService {

	List<UserDTO> getUsers();
	
	UserDetailsDTO getUserDetailsById(Long userId);
	
	UserDTO createUser(User user);

	UserDTO updateUser(User user);

	UserDTO authenticateUser(User user);
	
	List<AddressDTO> getAllUserAddresses(Long userId);
}
