package com.pablotelles.pokemonservice.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pablotelles.pokemonservice.entity.Pokemon;
import com.pablotelles.pokemonservice.exceptions.PokemonNotFoundException;
import com.pablotelles.pokemonservice.feignclients.ReviewFeignClient;
import com.pablotelles.pokemonservice.model.Review;
import com.pablotelles.pokemonservice.repository.PokemonRepository;
import com.pablotelles.pokemonservice.service.PokemonService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    private final RestTemplate restTemplate;

    private final ReviewFeignClient reviewFeignClient;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository, RestTemplate restTemplate, ReviewFeignClient reviewFeignClient) {
        this.pokemonRepository = pokemonRepository;
        this.restTemplate = restTemplate;
        this.reviewFeignClient = reviewFeignClient;
    }

    @Override
    public Pokemon createPokemon(Pokemon pokemon) {
        log.info("Saving new pokemon...{}",pokemon.getName());
        return pokemonRepository.save(pokemon);
    }

    @Override
    public Pokemon getPokemonById(Long id) {
        log.info("Fetching pokemon with the given id: {}", id);
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon Not found"));
        return pokemon;
    }

    @Override
    public List<Pokemon> getAllPokemon() {
        log.info("Fetching all pokemon");
        return pokemonRepository.findAll();
    }

    @Override
    public Pokemon updatePokemon(Pokemon pokemon, Long id) {
        log.info("Updating pokemon with the given id: {}",id);
        Pokemon p = pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon Not found"));
        p.setName(pokemon.getName());
        p.setType(pokemon.getType());
        
        Pokemon updatedPokemon = pokemonRepository.save(p);
        return updatedPokemon;
    }

    @Override
    public void deletePokemon(Long id) {
        Pokemon pokemonToDelete = pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon Not found"));
        pokemonRepository.delete(pokemonToDelete);
    }

    @Override
    public List<Review> getReviews(Long pokemonId){
        List<Review> reviews = restTemplate.getForObject("http://review-service/api/v1/review/pokemon/"+pokemonId, List.class);
        return reviews;
    }

    @Override
    public Review saveReview(Long pokemonId, Review review){
        review.setPokemonId(pokemonId);
        Review newReview = reviewFeignClient.saveReview(review);
        return newReview;
    }

    @Override
    public Map<String, Object> getPokemonAndReviews(Long pokemonId) {
        Map<String, Object> result = new HashMap<>();
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElse(null);
        if(pokemon==null){
            result.put("Mensaje:", "No existe el usuario");
            return result;
        }
        result.put("Pokemon", pokemon);
        List<Review> reviews = reviewFeignClient.getReviews(pokemonId);
        if(reviews.isEmpty()){
            result.put("Reviews", "Este pokemon no tiene reviews");
        }else{
            result.put("Reviews", reviews);
        }
        return result;
    }

}
