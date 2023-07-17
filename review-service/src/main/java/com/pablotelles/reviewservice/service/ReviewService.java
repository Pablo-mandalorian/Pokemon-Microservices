package com.pablotelles.reviewservice.service;

import java.util.List;

import com.pablotelles.reviewservice.entity.Review;

public interface ReviewService {
    Review createReview(Review review);
    Review getReviewById(Long id);
    List<Review> getAllReview();
    Review updateReview(Review review, Long id);
    void deleteReview(Long id);
    List<Review> getByPokemonId(Long pokemonId);
}
