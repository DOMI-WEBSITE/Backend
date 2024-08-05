package com.c1848tjavaangular.domi.exceptions;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public class JwtAuthenticationException extends AuthenticationCredentialsNotFoundException {

    public JwtAuthenticationException(String message){
        super(message);
    }
}
