package com.example.learningspringbootwebclient.rickandmortyapi;

public record Character(
        int id,
        String name,
        String species
) {
}
