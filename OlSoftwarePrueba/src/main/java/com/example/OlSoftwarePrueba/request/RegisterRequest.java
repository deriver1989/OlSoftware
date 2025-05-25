package com.example.OlSoftwarePrueba.request;

public record RegisterRequest(
        String name,
        String email,
        String password,
        String rol
) {
}
