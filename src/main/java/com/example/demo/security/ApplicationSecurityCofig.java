package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.demo.security.auth.ApplicationUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityCofig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;

//	@Autowired
//	private DataSource dataSource;
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery("select email,password,enabled " + "from bael_users " + "where email = ?")
//				.authoritiesByUsernameQuery("select email,authority " + "from authorities " + "where email = ?");
//	}

	@Autowired
	public ApplicationSecurityCofig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		this.passwordEncoder = passwordEncoder;
		this.userDetailsService = userDetailsService;
	}

	/**
	 * If we do not autowired password encoder then it will give error during
	 * authentication like required password encoder i.e BCrypt in console
	 **/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // If this csrf not disable then post/delete/put method will throws Forbidden
								// access
				.authorizeRequests().antMatchers("/", "index", "/css/*", "/js/*").permitAll()
				// .antMatchers(HttpMethod.DELETE,
				// "/api/v1/customers/*").hasAuthority(ApplicationUserPermission.CUSTOMER_WRITE.name())
				// .antMatchers("/api/v1/customers/*").hasRole(ApplicationUserRole.CUSTOMER.name())
				.anyRequest().authenticated().and().httpBasic(); // Default spring authentication is form based
																	// authentication, here we have set to basic
																	// authentication
	}

//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		UserDetails vijayUsr = User.builder()
//				.username("vijay")
//				.password(passwordEncoder.encode("password"))
//				.roles(ApplicationUserRole.CUSTOMER.name())
//				.authorities(ApplicationUserRole.CUSTOMER.getAuthority())
//				.build();
//		
//		UserDetails adminUsr = User.builder()
//				.username("admin")
//				.password(passwordEncoder.encode("password"))
//				.roles(ApplicationUserRole.ADMIN.name())
//				.authorities(ApplicationUserRole.ADMIN.getAuthority())
//				.build();
//				
//		return new InMemoryUserDetailsManager(vijayUsr,adminUsr);
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}

}
