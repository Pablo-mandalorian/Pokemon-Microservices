package com.pablotelles.identityservice.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pablotelles.identityservice.entity.UserCredential;
import com.pablotelles.identityservice.repository.UserCredentialRepository;
import com.pablotelles.identityservice.service.AuthService;

public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserCredentialRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    @Override
    public String saveUser(UserCredential userCredential) {
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        repository.save(userCredential);
        return "user added to the system";
    }

    @Override
    public String generateToken(String username) {
        return jwtServiceImpl.generateToken(username);    
    }

    @Override
    public void validateToken(String token) {
        jwtServiceImpl.validateToken(token);
    }
    
}
