package br.com.fiap.co_mandas.model;

import java.math.BigDecimal;
import java.util.Random;

public class Dish {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private BigDecimal price;
    // private Long id_restaurant;

    public Dish(Long id, String name, String description, BigDecimal price, Category category) {
        this.id = Math.abs(new Random().nextLong()); // gerando um id aleatório
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        // this.id_restaurant = id_restaurant;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}