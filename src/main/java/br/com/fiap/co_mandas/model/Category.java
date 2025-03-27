package br.com.fiap.co_mandas.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    PRATO_PRINCIPAL("Prato principal"),
    SOBREMESA("Sobremesa"),
    ENTRADA("Entrada");

    private final String descricao;

    Category(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue // Faz com que essa seja a representação do enum no JSON
    public String getDescricao() {
        return descricao;
    }
}
