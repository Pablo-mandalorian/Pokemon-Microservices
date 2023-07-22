package com.pablotelles.identityservice.service;

public interface JwtService {
    public void validateToken(final String token);
    public String generateToken(String userName);
}