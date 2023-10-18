package com.example.learningspringbootwebclient.rickandmortyapi;

import java.util.List;

public record ApiResponse(
        List<Character> results
) {
}
