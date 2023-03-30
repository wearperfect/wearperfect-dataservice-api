package com.wearperfect.dataservice.api.security.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearperfect.dataservice.api.security.models.WearperfectUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.entity.Role;
import com.wearperfect.dataservice.api.entity.User;
import com.wearperfect.dataservice.api.repository.UserRepository;
import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;
import com.wearperfect.dataservice.api.security.service.WearperfectUserDetailsService;
import com.wearperfect.dataservice.api.specification.UserDetailsSpecification;

@Service
@Transactional
public class WearperfectUserDetailsServiceImpl implements WearperfectUserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public WearperfectUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<User> users = userRepository
				.findAll(UserDetailsSpecification.userMobileOrEmailOrUsernamePredicate(username));

		if (users.size() < 1) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		} else if (users.size() > 1) {
			throw new HttpClientErrorException(HttpStatus.CONFLICT);
		} else {
			User user = users.get(0);
			return new WearperfectUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getPasswordLastUpdatedOn(),true, true, true, user.getActive(), mapRolesToAuthorities(user.getRoleDetails()));
		}
	}

	@Override
	public WearperfectUserPrincipal getLoggedInUserDetails() throws UsernameNotFoundException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("WearperfectUserPrincipal:getLoggedInUserDetails: "+mapper.writeValueAsString(authentication));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return (WearperfectUserPrincipal) authentication.getPrincipal();
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
		Collection<Role> roles = new ArrayList<Role>();
		roles.add(role);
		return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
	}

}
