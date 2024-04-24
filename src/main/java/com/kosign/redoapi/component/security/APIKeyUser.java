package com.kosign.redoapi.component.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class APIKeyUser extends UsernamePasswordAuthenticationToken {

    public APIKeyUser(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public APIKeyUser(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
