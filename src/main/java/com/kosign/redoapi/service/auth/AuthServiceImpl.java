package com.kosign.redoapi.service.auth;

import com.kosign.redoapi.component.security.JwtTokenUtil;
import com.kosign.redoapi.component.security.UserAuthenticationProvider;
import com.kosign.redoapi.payload.auth.AuthRequest;
import com.kosign.redoapi.payload.auth.AuthResponse;
import com.kosign.redoapi.payload.auth.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserAuthenticationProvider userAuthenticationProvider;
    public final JwtTokenUtil jwtTokenUtil;

    @Override
    public Object login(AuthRequest payload) throws Throwable {
        // Perform authentication
        Authentication authenticate = userAuthenticationProvider.authenticate(payload.username(), payload.password());

        SecurityUser securityUser = (SecurityUser) authenticate.getPrincipal();
        // Set the authenticated user in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        // Generate JWT token
        String token = jwtTokenUtil.doGenerateToken(securityUser);
        // Return the token in the response
        return new AuthResponse(token, "Bearer", jwtTokenUtil.getExpireIn());
    }
}
