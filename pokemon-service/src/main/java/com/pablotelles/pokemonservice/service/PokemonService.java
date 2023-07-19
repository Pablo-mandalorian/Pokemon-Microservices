package com.pablotelles.pokemonservice.service;

import java.util.List;
import java.util.Map;

import com.pablotelles.pokemonservice.entity.Pokemon;
import com.pablotelles.pokemonservice.model.Review;

public interface PokemonService {
    Pokemon createPokemon(Pokemon pokemon);
    Pokemon getPokemonById(Long id);
    List<Pokemon> getAllPokemon();
    Pokemon updatePokemon(Pokemon pokemon, Long id);
    void deletePokemon(Long id);
    List<Review> getReviews(Long pokemonId);
    Review saveReview(Long pokemonId, Review review);
    Map<String,Object> getPokemonAndReviews(Long pokemonId);
}
