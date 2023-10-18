package com.example.learningspringbootwebclient.rickandmortyapi;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class CharacterService {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();

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
}
