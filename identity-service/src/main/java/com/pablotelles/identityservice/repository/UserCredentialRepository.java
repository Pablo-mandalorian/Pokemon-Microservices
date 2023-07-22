package com.pablotelles.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pablotelles.identityservice.entity.UserCredential;
import java.util.Optional;


public interface UserCredentialRepository extends JpaRepository<UserCredential,Long>{
    Optional<UserCredential> findByName(String name);
}
