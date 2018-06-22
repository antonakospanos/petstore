package com.eurodyn.hr.petstore.web.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class PetStoreAuthenticationToken extends AbstractAuthenticationToken {

    public PetStoreAuthenticationToken() {
        super(Collections.emptyList());
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public PetStoreAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    private Object token;
    private Object user;

    public void setCredentials(Object token) {
        this.token = token;
    }

    public void setPrincipal(Object user) {
        this.user = user;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }
}
