package com.wearperfect.dataservice.api.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;

public interface WearperfectUserDetailsService extends UserDetailsService{

	WearperfectUserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException;
	
	WearperfectUserDetails getLoggedInUserDetails() throws UsernameNotFoundException;

}
