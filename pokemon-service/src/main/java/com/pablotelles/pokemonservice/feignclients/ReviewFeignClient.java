package com.pablotelles.pokemonservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pablotelles.pokemonservice.model.Review;

@FeignClient(name = "review-service", url = "http://localhost:8002/api/v1/review")
public interface ReviewFeignClient {
    
    @PostMapping("/create")
    Review saveReview(@RequestBody Review review);

    @GetMapping("/pokemon/{pokemonId}")
    List<Review> getReviews(@PathVariable("pokemonId") Long pokemonId);
}
