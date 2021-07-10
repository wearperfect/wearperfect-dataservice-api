package com.wearperfect.dataservice.api.security.models;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class WearperfectUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long userId;

	String username;

	String password;
	
	Date passwordLastUpdatedOn;

	Boolean accountNonExpired;

	Boolean accountNonLocked;

	Boolean credentialsNonExpired;

	Boolean enabled;

	Collection<? extends GrantedAuthority> authorities;

	public WearperfectUserDetails(Long userId, String username, String password,
			Date passwordLastUpdatedOn, Boolean accountNonExpired,
			Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled,
			Collection<? extends GrantedAuthority> collection) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.passwordLastUpdatedOn =passwordLastUpdatedOn;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.authorities = collection;
	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public Date getPasswordLastUpdatedOn() {
		return passwordLastUpdatedOn;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

}
