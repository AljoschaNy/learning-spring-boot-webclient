package com.example.learningspringbootwebclient.rickandmortyapi;

import java.util.List;

public record ApiResponse(
        ApiInfo info,
        List<Character> results
) {
}
