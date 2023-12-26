package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.UserRegistrationDto;
import com.example.springdatabasicdemo.enums.Role;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.models.UserRole;
import com.example.springdatabasicdemo.repositories.UserRepo;
import com.example.springdatabasicdemo.repositories.UserRoleRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepo userRepository;

    private UserRoleRepo userRoleRepository;


    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepository, UserRoleRepo userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserRegistrationDto registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byUsername = this.userRepository.findByUsername(registrationDTO.getUsername());

        if (byUsername.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var userRole = userRoleRepository.
                findUserRoleByRole(Role.User).orElseThrow();

        User user = new User(
                registrationDTO.getUsername(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getFirstname(),
                registrationDTO.getLastname(),
                registrationDTO.getImageUrl()
        );

        user.setUserRole(userRole);

        this.userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
