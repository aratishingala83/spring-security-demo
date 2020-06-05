package com.example.demo.security.auth;

import java.util.Optional;

public interface ApplicationUserAutheticationDao {
	
	Optional<ApplicationUserDetails> loadUserByUsername (String username); 
}
