package com.kosign.redoapi.global.config;


import com.kosign.redoapi.component.security.AccessDeniedHandler;
import com.kosign.redoapi.component.security.CustomJwtAuthenticationConverter;
import com.kosign.redoapi.component.security.UnauthorizedHandler;
import com.kosign.redoapi.properties.RsaKeysProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager userAuthProvider(
            PasswordEncoder passwordEncoder,
            @Qualifier("userAuthService")
            UserDetailsService userDetailsService
    ) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setHideUserNotFoundExceptions(false);
        return new ProviderManager(authProvider);
    }


    private static final List<String> PERMIT_ALL = List.of(
           "/api/ca/v1/auth/login",
            "/api/v1/image/**",
            "/api/v1/demo"
    );

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            UnauthorizedHandler unauthorizedHandler,
            AccessDeniedHandler accessDeniedHandler,
            CustomJwtAuthenticationConverter customJwtAuthenticationConverter
    ) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(configurer ->
                        configurer.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                        PERMIT_ALL.stream()
                                .map(AntPathRequestMatcher::antMatcher)
                                .toArray(RequestMatcher[]::new)
                        ).permitAll()
                        .requestMatchers("/api/ca/**").hasAnyAuthority("DEV","PO","ADMIN")
                        .requestMatchers("/api/bo/**").hasAnyAuthority("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(ex ->
                        ex.accessDeniedHandler(accessDeniedHandler)
                                .authenticationEntryPoint(unauthorizedHandler)
                )
                .sessionManagement(session  ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationEntryPoint(unauthorizedHandler)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .jwtAuthenticationConverter(customJwtAuthenticationConverter)
                        )
                )
                .build();
    }

    @Bean
    JwtDecoder jwtDecoder(RsaKeysProperties rsaKeysProperties) {
        return NimbusJwtDecoder.withPublicKey(rsaKeysProperties.publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder(RsaKeysProperties rsaKeysProperties) {
        JWK jwk= new RSAKey.Builder(rsaKeysProperties.publicKey()).privateKey(rsaKeysProperties.privateKey()).build();
        JWKSource<SecurityContext> jwkSource= new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

}