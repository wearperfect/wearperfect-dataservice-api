package com.wearperfect.dataservice.api.security.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearperfect.dataservice.api.entities.Role;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.security.service.CustomUserDetailsService;
import com.wearperfect.dataservice.api.specifications.UserDetailsSpecification;

@Service
@Transactional
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<User> users = userRepository.findAll(UserDetailsSpecification.userMobileOrEmailOrUsernamePredicate(username));
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			System.out.println(">>>>>>>>>>"+mapper.writeValueAsString(users.get(0)));
//		} catch (JsonProcessingException e1) {
//			// TODO Auto-generated catch block
//			System.out.println(">>>>>>>>");
//			e1.printStackTrace();
//		}
		if (users.size() < 1) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		} else if (users.size() > 1) {
			throw new HttpClientErrorException(HttpStatus.CONFLICT);
		} else {
			org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(users.get(0).getUsername(),
					users.get(0).getPassword(), mapRolesToAuthorities(users.get(0).getRoleDetails()));
//			try {
//				System.out.println("@@@@@@"+mapper.writeValueAsString(userDetails));
//			} catch (JsonProcessingException e) {
//				System.out.println("Exception@@@@@@");
//				e.printStackTrace();
//			}
			return userDetails;
		}
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
		Collection<Role> roles = new ArrayList<Role>();
		roles.add(role);
		return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
	}

}
