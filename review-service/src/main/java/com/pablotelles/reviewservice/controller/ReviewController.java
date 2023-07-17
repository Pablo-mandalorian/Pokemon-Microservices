package com.pablotelles.reviewservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pablotelles.reviewservice.entity.Review;
import com.pablotelles.reviewservice.service.implementation.ReviewServiceImpl;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    
    private final ReviewServiceImpl reviewServiceImpl;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewServiceImpl) {
        this.reviewServiceImpl = reviewServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviews(){
        return new ResponseEntity<>(reviewServiceImpl.getAllReview(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable("id") Long id){
        return new ResponseEntity<>(reviewServiceImpl.getReviewById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        return new ResponseEntity<>(reviewServiceImpl.createReview(review), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Review> updateReview(@RequestBody Review review, @PathVariable("id") Long id){
        return ResponseEntity.ok(reviewServiceImpl.updateReview(review,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") Long id){
        reviewServiceImpl.deleteReview(id);
        return new ResponseEntity<>("Review Deleted", HttpStatus.ACCEPTED);
    }

    @GetMapping("/pokemon/{pokemonId}")
    public ResponseEntity<List<Review>> getAllReviewsByPokemonId(@PathVariable("pokemonId") Long pokemonId){
        return new ResponseEntity<>(reviewServiceImpl.getByPokemonId(pokemonId), HttpStatus.OK);
    }

}
