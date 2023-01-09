package com.wearperfect.dataservice.api.security.service;

import com.wearperfect.dataservice.api.security.models.WearperfectUserPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;

public interface WearperfectUserDetailsService extends UserDetailsService{

	WearperfectUserDetails loadUserByUsername(String username);

	WearperfectUserPrincipal getLoggedInUserDetails() throws UsernameNotFoundException;

}
