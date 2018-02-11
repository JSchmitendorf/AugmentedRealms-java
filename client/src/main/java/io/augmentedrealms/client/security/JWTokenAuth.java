package io.augmentedrealms.client.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JWTokenAuth extends AbstractAuthenticationToken {

    private String email;

    private String token;

    public JWTokenAuth(String email, String token) {
        super(null);
        this.email = email;
        this.token = token;
    }


    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }
}
