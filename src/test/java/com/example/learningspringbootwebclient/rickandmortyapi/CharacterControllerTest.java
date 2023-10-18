package com.example.learningspringbootwebclient.rickandmortyapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CharacterControllerTest {

    private static MockWebServer mockWebServer;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void init() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry){
        registry.add("rickandmorty.characters.baseUrl", () -> mockWebServer.url("/").toString());
    }
    @Test
    void getAllCharacters() throws Exception{
        List<Character> characters = List.of(
                new Character(1, "test", "test"),
                new Character(2, "tes2", "tes2"),
                new Character(3, "tes3", "tes3")
        );
        String charactersAsJson = objectMapper.writeValueAsString(characters);

        MockResponse response = new MockResponse();
        response.setBody(objectMapper.writeValueAsString(new ApiResponse(new ApiInfo(3), characters)));
        response.addHeader("Content-Type","application/json");

        mockWebServer.enqueue(response);

        mockMvc.perform(get("/api/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json(charactersAsJson));
    }

    @Test
    void getCharacterById() {
    }

    @Test
    void findCharactersByStatus() {
    }

    @Test
    void getTotalAmountOfCharactersBySpecies() {
    }
}