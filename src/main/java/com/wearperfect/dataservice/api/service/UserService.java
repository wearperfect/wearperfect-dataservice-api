package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.entities.User;

public interface UserService {

	List<User> getUsers();
	
	User getUserDetailsById(Long userId);
	
	User createUser(User user);

	User updateUser(User user);

	User authenticateUser(User user);
}
