package com.pablotelles.pokemonservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pablotelles.pokemonservice.model.Review;

@FeignClient(name = "review-service")
public interface ReviewFeignClient {
    
    @PostMapping("/api/v1/review/create")
    Review saveReview(@RequestBody Review review);

    @GetMapping("/api/v1/review/pokemon/{pokemonId}")
    List<Review> getReviews(@PathVariable("pokemonId") Long pokemonId);
}
