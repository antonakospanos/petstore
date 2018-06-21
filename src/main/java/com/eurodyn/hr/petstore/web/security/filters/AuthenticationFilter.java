package com.eurodyn.hr.petstore.web.security.filters;

import com.eurodyn.hr.petstore.web.security.authentication.AuthenticationDetails;
import com.eurodyn.hr.petstore.web.security.authentication.PetStoreAuthenticationDetailsSource;
import com.eurodyn.hr.petstore.web.security.authentication.PetStoreAuthenticationToken;
import com.eurodyn.hr.petstore.web.security.authentication.AuthenticationDetails;
import com.eurodyn.hr.petstore.web.security.authentication.AuthenticationDetails;
import com.eurodyn.hr.petstore.web.security.authentication.PetStoreAuthenticationDetailsSource;
import com.eurodyn.hr.petstore.web.security.authentication.PetStoreAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {

    private PetStoreAuthenticationDetailsSource petStoreAuthenticationDetailsSource;
    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authManager) {
        this.authenticationManager = authManager;
        this.petStoreAuthenticationDetailsSource = new PetStoreAuthenticationDetailsSource();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (authenticate(httpRequest)) {
            chain.doFilter(request, response);
        }
    }

    private boolean authenticate(HttpServletRequest request) {
        // Build AuthenticationDetails parsing HTTP Authorization header
        AuthenticationDetails authDetails = this.petStoreAuthenticationDetailsSource.buildDetails(request);

        // Build petStoreAuthenticationToken using AuthenticationDetails
        PetStoreAuthenticationToken authToken = new PetStoreAuthenticationToken();
        authToken.setDetails(authDetails);

        // Authenticate request using the list of the authentication manager's authentication providers (petStoreAuthenticationProvider)
        Authentication authResult = this.authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authResult);

        return true;
    }
}
