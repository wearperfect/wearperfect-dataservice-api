package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.service.UserService;
import com.wearperfect.dataservice.api.specifications.UserDetailsSpecification;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	public List<UserDTO> getUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user->userMapper.mapUserToUserDto(user)).collect(Collectors.toList());
	}

	@Override
	public UserDTO getUserDetailsById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return userMapper.mapUserToUserDto(user.get());
		} else {
			return null;
		}
	}

	@Override
	public UserDTO createUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(BCryptVersion.$2Y, 12);
		if(null == user.getUsername() || user.getUsername().isEmpty() || null == user.getPassword()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		user.setUsername(user.getUsername().toLowerCase());
		if(null != user.getEmail() && !user.getEmail().isEmpty()) {
			user.setEmail(user.getEmail().toLowerCase());
		}
		// Validate Email
		// Validate Phone number
		// Validate username
		// Validate password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoleId(2); //Default role USER
		user.setCreatedOn(new Date());
		user.setActive(true);
		
		try {
			System.out.println(">>>>>>>>>"+objectMapper.writeValueAsString(user));
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
		if(null == user.getId()) {
			return null;
		}
		user.setLastUpdatedOn(new Date());
		User updatedUser = userRepository.saveAndFlush(user);
		return userMapper.mapUserToUserDto(updatedUser);
	}

	@Override
	public UserDTO authenticateUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(BCryptVersion.$2Y, 12);
		List<User> users = userRepository.findAll(UserDetailsSpecification.userMobileOrEmailOrUsernamePredicate(user.getUsername().toLowerCase()));
		if(users.size()==1 && passwordEncoder.matches(user.getPassword(), users.get(0).getPassword())) {
			return userMapper.mapUserToUserDto(users.get(0));
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

}
