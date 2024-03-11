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

	/**
	import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.util.List;

public class RestClient {

    private static final CookieStore cookieStore = new BasicCookieStore();

    public static void main(String[] args) {
        // Make login request
        // Modify this part based on your actual login request
        HttpResponse loginResponse = makeLoginRequest();

        // Update cookie store with received cookies
        updateCookieStore(loginResponse);

        // Use HttpClient to make subsequent requests
        // Example: HttpGet request = new HttpGet("http://example.com/api/resource");
        //          httpClient.execute(request);
    }

    static HttpResponse makeLoginRequest() {
        // Make login request
        // Modify this part based on your actual login request
        return null; // Placeholder, replace with actual login request and response
    }

    static void updateCookieStore(HttpResponse response) {
        Header[] headers = response.getHeaders("Set-Cookie");
        for (Header header : headers) {
            String cookieStr = header.getValue();
            String[] parts = cookieStr.split(";\\s*");

            // Parse cookie name and value
            String[] nameValue = parts[0].split("=", 2);
            String name = nameValue[0];
            String value = nameValue[1];

            // Create BasicClientCookie and add to cookie store
            BasicClientCookie cookie = new BasicClientCookie(name, value);
            for (int i = 1; i < parts.length; i++) {
                String[] attribute = parts[i].split("=", 2);
                String attributeName = attribute[0].trim();
                String attributeValue = attribute.length > 1 ? attribute[1].trim() : null;
                if (attributeValue != null) {
                    switch (attributeName) {
                        // Handle other cookie attributes as needed
                        case "Expires":
                            cookie.setExpiryDate(org.apache.http.client.utils.DateUtils.parseDate(attributeValue));
                            break;
                        case "Path":
                            cookie.setPath(attributeValue);
                            break;
                        case "Domain":
                            cookie.setDomain(attributeValue);
                            break;
                        // Add additional cases as needed
                    }
                }
            }
            cookieStore.addCookie(cookie);
        }
    }
}
**/

}
