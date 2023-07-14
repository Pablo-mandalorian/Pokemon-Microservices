package com.pablotelles.pokemonservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pablotelles.pokemonservice.entity.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon,Long>{
    
}
