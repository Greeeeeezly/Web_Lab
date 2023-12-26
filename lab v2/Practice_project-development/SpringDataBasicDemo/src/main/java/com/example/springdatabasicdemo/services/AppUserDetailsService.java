package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.repositories.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.stream.Collectors;

public class AppUserDetailsService implements UserDetailsService {
    private UserRepo userRepository;

    public AppUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(userName).map((u) -> {
            return new User(u.getUsername(), u.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+u.getUserRole().getRole().toString())));
        }).orElseThrow(() -> {
            return new UsernameNotFoundException(userName + " was not found!");
        });
    }
}
