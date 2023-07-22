package com.pablotelles.identityservice.service;

import com.pablotelles.identityservice.entity.UserCredential;

public interface AuthService {
    public String saveUser(UserCredential userCredential);
    public String generateToken(String username);
    public void validateToken(String token);
}
