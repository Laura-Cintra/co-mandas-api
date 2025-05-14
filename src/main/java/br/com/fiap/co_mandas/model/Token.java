package br.com.fiap.co_mandas.model;

public record Token (
    String token,
    String email,
    RoleType role
){}