package com.kosign.redoapi.component.security;

import com.kosign.redoapi.payload.auth.SecurityUser;
import com.kosign.redoapi.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    private final JwtEncoder jwtEncoder;
    private final JwtProperties jwtConfig;

    public long getExpireIn(){
        return jwtConfig.expiration().getSeconds();
    }

    public String doGenerateToken(SecurityUser securityUser ){

        Instant instant= Instant.now();

        Map<String, Object> claim = new HashMap<>();
        claim.put("name", securityUser.getUsername());
//        claim.put("scope",securityUser.user().getRole());

        JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .subject(securityUser.getUsername())
//                .issuer(securityUser.user().get)
                .issuedAt(instant)
                .expiresAt(instant.plus(jwtConfig.expiration().getSeconds(), ChronoUnit.SECONDS))
                .claims(c -> c.putAll(claim))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

}
