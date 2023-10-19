package com.example.learningspringbootwebclient.rickandmortyapi;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService characterService;
    @GetMapping
    public List<Character> getAllCharacters(@RequestParam(required=false) String page){
        if(page!=null) {
            return characterService.getAllCharacters(Integer.parseInt(page));
        }
        return characterService.getAllCharacters();
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable int id) {
        return characterService.getCharacterById(id);
    }

    @GetMapping("/species")
    public List<Character> findCharactersByStatus(@RequestParam String species) {
        return characterService.findCharactersBySpecies(species);
    }

    @GetMapping("/species-statistic")
    public int getTotalAmountOfCharactersBySpecies(@RequestParam String specie) {
        return characterService.getTotalAmountOfCharactersBySpecies(specie);
    }
}
