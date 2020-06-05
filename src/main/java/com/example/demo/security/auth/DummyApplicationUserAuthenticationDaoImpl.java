package com.example.demo.security.auth;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.security.ApplicationUserRole;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Component("dummyDao")
public class DummyApplicationUserAuthenticationDaoImpl implements ApplicationUserAutheticationDao {
	
	
	public final PasswordEncoder passwordEncoder;
	
	@Autowired
	public DummyApplicationUserAuthenticationDaoImpl(PasswordEncoder passwordEncoder){
		this.passwordEncoder = passwordEncoder;
	}
	

	private List<ApplicationUserDetails> getApplicationUsers() {
		final List<ApplicationUserDetails> userDetails = Lists.newArrayList();
		//Please make note that order of argument mattern when you pass 
		//'username, password, authorities etc' in ApplicationUser otherwise you will face issue like Forbidden access
		ApplicationUserDetails vijayUsr = 
				new ApplicationUserDetails(
						"vijay",
						passwordEncoder.encode("password"),
						ApplicationUserRole.CUSTOMER.getAuthority(),
						true,
						true,
						true,
						true
						);
		ApplicationUserDetails adminUsr = 
				new ApplicationUserDetails(
						"admin",
						passwordEncoder.encode("password"),
						ApplicationUserRole.ADMIN.getAuthority(),
						true,
						true,
						true,
						true
						);
//				User.builder()
//				.username("vijay")
//				.roles(ApplicationUserRole.CUSTOMER.name())
//				.authorities(ApplicationUserRole.CUSTOMER.getAuthority())
//				.build();
		
//		UserDetails adminUsr = User.builder()
//				.username("admin")
//				.roles(ApplicationUserRole.ADMIN.name())
//				.authorities(ApplicationUserRole.ADMIN.getAuthority())
//				.build();
		userDetails.add(vijayUsr);
		userDetails.add(adminUsr);
		return userDetails;
	}

	@Override
	public Optional<ApplicationUserDetails> loadUserByUsername(String username) {
		return getApplicationUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst();
	}

}
