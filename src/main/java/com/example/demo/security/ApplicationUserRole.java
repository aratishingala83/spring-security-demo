package com.example.demo.security;

import static com.example.demo.security.ApplicationUserPermission.ACCOUNT_READ;
import static com.example.demo.security.ApplicationUserPermission.ACCOUNT_WRITE;
import static com.example.demo.security.ApplicationUserPermission.CUSTOMER_READ;
import static com.example.demo.security.ApplicationUserPermission.CUSTOMER_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
	

	CUSTOMER(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(CUSTOMER_READ,CUSTOMER_WRITE,ACCOUNT_READ,ACCOUNT_WRITE));
	
	private final Set<ApplicationUserPermission> permissions;
	
	ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<ApplicationUserPermission> getPermissions() {
		return this.permissions;
	}
	
	
	public Set<SimpleGrantedAuthority> getAuthority() {
		 Set<SimpleGrantedAuthority> authorities = this.getPermissions()
				.stream()
				.map(authority->new SimpleGrantedAuthority(authority.getPermission()))
				.collect(Collectors.toSet());
		 authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));// If you will not add this then you will get permission forbidden error
		 return authorities;
		 
	}
	
	

}
