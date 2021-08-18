package com.au.post.suburb.suburbservice.filter;

import com.au.post.suburb.suburbservice.service.UserInfoService;
import com.au.post.suburb.suburbservice.util.JwtTokenUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author bnaragani created on 16/08/2021
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // check if the authorization header have the format "Bearer 2fdfgdf"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // will extract the token, leaving Bearer''
            username = jwtTokenUtil.extractUsername(jwt);
        }

        // if the user doesn't have an authentication in the context, validate and assign authentication token to the user context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //Get the user details by username
            UserDetails userDetails = this.userInfoService.loadUserByUsername(username);

            // validate if the token is valid for the given username
            if (jwtTokenUtil.validateToken(jwt, userDetails)) {

                //Build the authentication token
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                //set the token to the context
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
