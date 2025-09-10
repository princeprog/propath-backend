package com.jobboard.propath.service;

import com.jobboard.propath.dto.LoginUserDto;
import com.jobboard.propath.dto.RegisterUserDto;
import com.jobboard.propath.entity.User;
import com.jobboard.propath.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User signup(RegisterUserDto input) {
        User user = new User()
                .setPhone(input.getPhone())
                .setFirstname(input.getFirstname())
                .setLastname(input.getLastname())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public String authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        User user = userRepository.findByEmail(input.getEmail())
                .orElseThrow();

        // Generate JWT token
        return jwtService.generateToken(user);
    }
}