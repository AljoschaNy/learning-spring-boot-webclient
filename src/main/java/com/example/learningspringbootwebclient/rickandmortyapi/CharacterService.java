package com.example.learningspringbootwebclient.rickandmortyapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class CharacterService {

    private final WebClient webClient;
    public CharacterService(@Value("${rickandmorty.characters.baseUrl}")String baseUrl){
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }


    public List<Character> getAllCharacters(int page){
        if(page < 0) {
            return null;
        }
        ApiResponse response = Objects.requireNonNull(webClient
                        .get()
                        .uri(uriBuilder -> uriBuilder.queryParam("page",page)
                                .build())
                        .retrieve()
                        .toEntity(ApiResponse.class)
                        .block())
                .getBody();
        assert response != null;
        return response.results();
    }
    public List<Character> getAllCharacters(){
        ApiResponse response = Objects.requireNonNull(webClient
                        .get()
                        .retrieve()
                        .toEntity(ApiResponse.class)
                        .block())
                .getBody();
        assert response != null;
        return response.results();
    }

    public Character getCharacterById(int id){
        Character response = Objects.requireNonNull(webClient
                        .get()
                        .uri(uriBuilder -> uriBuilder.pathSegment("/" + id)
                                .build())
                        .retrieve()
                        .toEntity(Character.class)
                        .block())
                .getBody();
        assert response != null;
        return response;
    }

    public List<Character> findCharactersBySpecies(String species) {
        ApiResponse response = Objects.requireNonNull(webClient
                        .get()
                        .uri(uriBuilder -> uriBuilder.queryParam("species",species)
                                .build())
                        .retrieve()
                        .toEntity(ApiResponse.class)
                        .block())
                .getBody();

        assert response != null;
        return response.results();
    }

    public int getTotalAmountOfCharactersBySpecies(String specie) {
        ApiResponse response = Objects.requireNonNull(webClient
                        .get()
                        .uri(uriBuilder -> uriBuilder.queryParam("species", specie)
                                .build())
                        .retrieve()
                        .toEntity(ApiResponse.class)
                        .block())
                .getBody();
        assert response != null;
        return response.info().count();
    }
}

