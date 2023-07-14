package com.pablotelles.pokemonservice.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pablotelles.pokemonservice.entity.Pokemon;
import com.pablotelles.pokemonservice.exceptions.PokemonNotFoundException;
import com.pablotelles.pokemonservice.repository.PokemonRepository;
import com.pablotelles.pokemonservice.service.PokemonService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
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
    
}
