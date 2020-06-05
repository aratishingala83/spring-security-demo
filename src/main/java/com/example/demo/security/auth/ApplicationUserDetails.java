package com.example.demo.security.auth;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ApplicationUserDetails implements UserDetails {

	private static final long serialVersionUID = -6360949472128531685L;
	public final Set<? extends GrantedAuthority> grantAuthorities;
	public final String password;
	public final String username;
	public final boolean isAccountNonExpired;
	public final boolean isAccountNonLocked;
	public final boolean isCredentialsNonExpired;
	public final boolean isEnable;

	public ApplicationUserDetails( String username, String password, Set<? extends GrantedAuthority> grantAuthorities,
			boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
			boolean isEnable) {
		this.grantAuthorities = grantAuthorities;
		this.password = password;
		this.username = username;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnable = isEnable;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnable;
	}

}
