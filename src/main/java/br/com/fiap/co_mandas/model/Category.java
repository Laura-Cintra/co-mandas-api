package br.com.fiap.co_mandas.model;

public enum Category {
    PRATO_PRINCIPAL("Prato principal"),
    SOBREMESA("Sobremesa"),
    ENTRADA("Entrada");

    private final String descricao;

    Category(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
