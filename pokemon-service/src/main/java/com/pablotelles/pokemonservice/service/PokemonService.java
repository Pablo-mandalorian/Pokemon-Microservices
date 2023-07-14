package com.pablotelles.pokemonservice.service;

import java.util.List;

import com.pablotelles.pokemonservice.entity.Pokemon;

public interface PokemonService {
    Pokemon createPokemon(Pokemon pokemon);
    Pokemon getPokemonById(Long id);
    List<Pokemon> getAllPokemon();
    Pokemon updatePokemon(Pokemon pokemon, Long id);
    void deletePokemon(Long id);
}
