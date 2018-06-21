package com.eurodyn.hr.petstore.web.security.authentication;

import com.eurodyn.hr.petstore.dao.model.User;
import com.eurodyn.hr.petstore.service.UserService;
import com.eurodyn.hr.petstore.web.configuration.SecurityConfiguration;
import com.eurodyn.hr.petstore.dao.model.User;
import com.eurodyn.hr.petstore.service.UserService;
import com.eurodyn.hr.petstore.web.configuration.SecurityConfiguration;
import com.eurodyn.hr.petstore.web.security.exception.PetStoreAuthenticationException;
import com.eurodyn.hr.petstore.web.support.SecurityHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * PetStoreAuthenticationProvider.
 */
@Component
public class PetStoreAuthenticationProvider implements AuthenticationProvider, Serializable {

	@Autowired
	private UserService userService;


	@Value("${application.roles.admin.access-token}")
	private String adminAccessToken;

	/**
	 * Authenticate requests to the Pet Store API
	 *
	 * @param authenticationRequest the authenticationRequest
	 *
	 * @return the authenticationRequest
	 * @throws AuthenticationException the authenticationRequest exception
	 */
	@Override
	public Authentication authenticate(Authentication authenticationRequest) throws AuthenticationException {
		AuthenticationDetails authDetails = (AuthenticationDetails) authenticationRequest.getDetails();

		User user = null;
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		String token = authDetails.getAccessToken();
		if (StringUtils.isBlank(token)) {
			// No token, grant access only to unprotected APIs
			authenticationRequest.setAuthenticated(true);
			authorities.add(new SimpleGrantedAuthority(SecurityConfiguration.ROLE_GUEST));
		} else if (adminAccessToken.equals(token)) {
			// Admin token
			authenticationRequest.setAuthenticated(true);
			authorities.add(new SimpleGrantedAuthority(SecurityConfiguration.ROLE_ADMIN));
		} else {
			// Validate that user's access token is listed in User table
			UUID accessToken = SecurityHelper.convertAccessToken(token);
			user = userService.find(accessToken);

			// Authorization algorithm!
			if (user != null) {
				authenticationRequest.setAuthenticated(true);
				authorities.add(new SimpleGrantedAuthority(SecurityConfiguration.ROLE_USER));
			} else {
				// authenticationRequest.setAuthenticated(false);
				throw new PetStoreAuthenticationException("Invalid token: " + token);
			}
		}

		PetStoreAuthenticationToken authenticationResponse = new PetStoreAuthenticationToken(authorities);
		authenticationResponse.setPrincipal(user);
		authenticationResponse.setAuthenticated(authenticationRequest.isAuthenticated());
		authenticationResponse.setDetails(authenticationRequest.getDetails());
		authenticationResponse.setCredentials(token);

		return authenticationResponse;
	}

	/**
	 * Authentication class supported
	 *
	 * @param authentication the authentication
	 * @return true, if successful
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return PetStoreAuthenticationToken.class.isAssignableFrom(authentication);
	}
}