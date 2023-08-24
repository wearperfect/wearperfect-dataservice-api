package com.wearperfect.dataservice.api.security.service;

import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;
import com.wearperfect.dataservice.api.security.models.WearperfectUserPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface WearperfectUserDetailsService extends UserDetailsService{

	WearperfectUserDetails loadUserByUsername(String username);

	WearperfectUserPrincipal getLoggedInUserDetails() throws UsernameNotFoundException;

    Optional<WearperfectUserPrincipal> getOptionalLoggedInUserDetails() throws UsernameNotFoundException;
}
