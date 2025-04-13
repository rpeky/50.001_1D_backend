package com.travelapp.api.security.controller;

import com.travelapp.api.security.DTO.AuthenticationRequest;
import com.travelapp.api.security.DTO.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
           @RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

