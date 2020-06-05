package com.example.demo.security.auth;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BaelUser;
import com.example.demo.reposiroty.ApplicationUserRespository;


@Service
public class ApplicationUserDetailService implements UserDetailsService {

	
	ApplicationUserRespository applicationUserRespository;
	PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationUserDetailService(ApplicationUserRespository applicationUserRespository,
			PasswordEncoder passwordEncoder) {
		this.applicationUserRespository = applicationUserRespository;
		this.passwordEncoder = passwordEncoder;
	} 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getApplicationUser(username);
	}
	
	private UserDetails getApplicationUser(String userName) {

		BaelUser user = applicationUserRespository.findAllByUsername(userName);
		ApplicationUserDetails applicationUserDetails = null;
		
		try {
			applicationUserDetails = new ApplicationUserDetails(
					user.getUsername(),
					passwordEncoder.encode(user.getPassword()),
					getAuthorities(user),
					true,
					true,
					true,
					user.getEnabled()==1?true:false
					);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
				
		return applicationUserDetails;
	}

	private Set<? extends GrantedAuthority> getAuthorities(BaelUser user) {
		return  user.getAuthorities()
				.stream()
				.map(authority->new SimpleGrantedAuthority(authority.getAuthority()))
				.collect(Collectors.toSet());
	}

	
	

}
