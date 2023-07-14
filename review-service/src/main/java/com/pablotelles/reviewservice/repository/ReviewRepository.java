package com.pablotelles.reviewservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pablotelles.reviewservice.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Long>{
    
}
