package com.project.libraryManagement.userManagement.configuration;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.project.libraryManagement.userManagement.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    JwtAuthenticationFilter(
        HandlerExceptionResolver handlerExceptionResolver,
        JwtService jwtService,
        UserDetailsService userDetailsService
    ) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest req,
        HttpServletResponse res,
        FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = req.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(req, res);
            return;
        }
        try {
            final String jwt = authHeader.substring(7);
            final String username = jwtService.getUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (username != null && authentication == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    // create a new authentication object with the user details
                    UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                     // set the details in the authentication object
                    // this is used to get the details of the request, like the IP address, session
                    // ID, etc.
                    WebAuthenticationDetails details = new WebAuthenticationDetailsSource().buildDetails(req);
                    authToken.setDetails(details);
                    // set the authentication object in security context
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
                else {
                    // handling invalid token
                    SecurityContextHolder.clearContext();
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    res.getWriter().write("Invalid Jwt Token");
                    return;
                }
            }
            filterChain.doFilter(req, res);
        }
        catch(Exception e) {
            handlerExceptionResolver.resolveException(req, res, null, e);
        }
    }

}
