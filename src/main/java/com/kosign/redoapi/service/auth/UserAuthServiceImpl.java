package com.kosign.redoapi.service.auth;

import com.kosign.redoapi.domain.user.UserInfoRepository;
import com.kosign.redoapi.payload.auth.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userAuthService")
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserDetailsService {
    private final UserInfoRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var user = userRepository.findByUserName(username).orElseThrow(
               ()-> new UsernameNotFoundException("User not found"));
        return new SecurityUser(user);
    }
}
