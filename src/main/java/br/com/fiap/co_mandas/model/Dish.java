package  br.com.fiap.co_mandas.model;

import java.util.Random;

public class Dish {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private Double price;
//    private Long id_restaurant;

    public Dish(Long id, String name, String description, Double price, Category category) {
        this.id = Math.abs(new Random().nextLong()); //gerando um id aleatório
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
//        this.id_restaurant = id_restaurant;
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

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}