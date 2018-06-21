package com.eurodyn.hr.petstore.web.security.exception;

import org.springframework.security.core.AuthenticationException;

public class PetStoreAuthenticationException extends AuthenticationException {

    public PetStoreAuthenticationException(String message) {
        super(message);
    }
}
