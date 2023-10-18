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
    public List<Character> getAllCharacters(@RequestParam int page){
        if(page!=0) {
            return characterService.getAllCharacters(page);
        }
        return characterService.getAllCharacters();
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable int id) {
        return characterService.getCharacterById(id);
    }

    @GetMapping("/status")
    public List<Character> findCharactersByStatus(@RequestParam String status) {
        return characterService.findCharactersByStatus(status);
    }
}
