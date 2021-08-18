package com.au.post.suburb.suburbservice.model;

/**
 * @author bnaragani created on 15/08/2021
 */
public class AuthenticationResponse {


    private String jwtToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public AuthenticationResponse(String jwt) {
        this.jwtToken = jwt;
    }


}
