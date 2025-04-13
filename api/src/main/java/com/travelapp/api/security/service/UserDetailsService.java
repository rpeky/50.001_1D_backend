package com.travelapp.api.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Replace with real user lookup logic; this is a dummy implementation.
        if ("user".equals(username)) {
            // Use a BCrypt‑hashed password for production
            return User.withUsername("user")
                    .password("$2a$10$DowcdKCmkSdX1examplehashedpassword")
                    .authorities(Collections.emptyList())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}

