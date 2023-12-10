package com.example.dorne.service.impl;


import com.example.dorne.model.entity.UserEntity;
import com.example.dorne.model.entity.enums.UserRoleEnum;
import com.example.dorne.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(AppUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found!"));
    }

    private static UserDetails map(UserEntity userEntity) {
        return User
                .withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(AppUserDetailsService.map(userEntity.getRole()))
                .build();

    }

    private static GrantedAuthority map(UserRoleEnum userRoleEnum) {
        return new SimpleGrantedAuthority("ROLE_" + userRoleEnum.name());
    }

}
