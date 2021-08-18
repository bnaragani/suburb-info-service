package com.au.post.suburb.suburbservice.controller;

import com.au.post.suburb.suburbservice.model.AuthenticationRequest;
import com.au.post.suburb.suburbservice.model.AuthenticationResponse;
import com.au.post.suburb.suburbservice.service.UserInfoService;
import com.au.post.suburb.suburbservice.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bnaragani created on 15/08/2021
 */
@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtToken;

    @Autowired
    private UserInfoService userDetailService;

    @GetMapping({"/hello"})
    public String testAuthentication() {
        return "Hello World";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST ,consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtToken.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
