package com.wearperfect.dataservice.api.security.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.entities.Role;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;
import com.wearperfect.dataservice.api.security.service.WearperfectUserDetailsService;
import com.wearperfect.dataservice.api.specifications.UserDetailsSpecification;

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
	public WearperfectUserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return new WearperfectUserDetails(user.get().getId(), user.get().getUsername(), user.get().getPassword(), user.get().getPasswordLastUpdatedOn(), true, true, true, user.get().getActive(), mapRolesToAuthorities(user.get().getRoleDetails()));
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
		Collection<Role> roles = new ArrayList<Role>();
		roles.add(role);
		return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
	}

	@Override
	public WearperfectUserDetails getLoggedInUserDetails() throws UsernameNotFoundException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		WearperfectUserDetails wearperfectUserDetails = (WearperfectUserDetails) authentication.getPrincipal();
		return wearperfectUserDetails;
	}

}
