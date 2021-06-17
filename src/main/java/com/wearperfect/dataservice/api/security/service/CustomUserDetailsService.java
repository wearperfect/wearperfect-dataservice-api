package com.wearperfect.dataservice.api.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wearperfect.dataservice.api.security.models.CustomUserDetails;

public interface CustomUserDetailsService extends UserDetailsService{

	CustomUserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException;
	
	CustomUserDetails getLoggedInUserDetails() throws UsernameNotFoundException;

}
