package com.pablotelles.reviewservice.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pablotelles.reviewservice.entity.Review;
import com.pablotelles.reviewservice.exceptions.ReviewNotFoundException;
import com.pablotelles.reviewservice.repository.ReviewRepository;
import com.pablotelles.reviewservice.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(Review review) {
        log.info("Creating Review with title: {}", review.getTitle());
        return reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(Long id) {
        log.info("Fetching review with id: {}", id);
        Review review = reviewRepository.findById(id).orElseThrow(()-> new ReviewNotFoundException("Review Not found"));
        return review;
    }

    @Override
    public List<Review> getAllReview() {
        log.info("Fetching all reviews");
        return reviewRepository.findAll();
    }

    @Override
    public Review updateReview(Review review, Long id) {
        Review review2 = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review Not Found"));
        review2.setTitle(review.getTitle());
        review2.setContent(review.getContent());
        review2.setStars(review.getStars());
        
        Review updatedReview = reviewRepository.save(review2);
        return updatedReview;    
    }

    @Override
    public void deleteReview(Long id) {
        Review review2 = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review Not Found"));
        reviewRepository.delete(review2);
    }

    @Override
    public List<Review> getByPokemonId(Long pokemonId) {
        return reviewRepository.findByPokemonId(pokemonId);
    }
    
}
