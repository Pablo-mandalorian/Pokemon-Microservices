package com.pablotelles.pokemonservice.controller;

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

import com.pablotelles.pokemonservice.entity.Pokemon;
import com.pablotelles.pokemonservice.model.Review;
import com.pablotelles.pokemonservice.service.implementation.PokemonServiceImpl;

@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonController {

    private final PokemonServiceImpl pokemonServiceImpl;

    @Autowired
    public PokemonController(PokemonServiceImpl pokemonServiceImpl) {
        this.pokemonServiceImpl = pokemonServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<List<Pokemon>> getAllPokemon(){
        return new ResponseEntity<List<Pokemon>>(pokemonServiceImpl.getAllPokemon(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable("id") Long id){
        return new ResponseEntity<Pokemon>(pokemonServiceImpl.getPokemonById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pokemon>  createPokemon(@RequestBody Pokemon pokemon){
        return new ResponseEntity<Pokemon>(pokemonServiceImpl.createPokemon(pokemon), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable("id") Long id){
        return ResponseEntity.ok(pokemonServiceImpl.updatePokemon(pokemon, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") Long id){
        pokemonServiceImpl.deletePokemon(id);
        return new ResponseEntity<String>("Pokemon Deleted", HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/reviews/{pokemonId}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable("pokemonId") Long pokemonId){
        return new ResponseEntity<List<Review>>(pokemonServiceImpl.getReviews(pokemonId), HttpStatus.OK);
    }
}
