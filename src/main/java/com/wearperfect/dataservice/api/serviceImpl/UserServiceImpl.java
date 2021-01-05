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
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.service.UserService;
import com.wearperfect.dataservice.api.specifications.UserDetailsSpecification;

@Transactional
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUserDetailsById(Long userId) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		user.setCreatedOn(new Date());
		try {
			System.out.println(">>>>>>>>>"+objectMapper.writeValueAsString(user));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		if(null == user.getId()) {
			return null;
		}
		user.setLastUpdatedOn(new Date());
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User authenticateUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(BCryptVersion.$2Y, 12);
		List<User> users = userRepository.findAll(UserDetailsSpecification.userMobileOrEmailOrUsernamePredicate(user.getUsername()));
		if(users.size()==1 && passwordEncoder.matches(user.getPassword(), users.get(0).getPassword())) {
			return users.get(0);
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

}
