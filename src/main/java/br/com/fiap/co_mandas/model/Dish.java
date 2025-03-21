package br.com.fiap.co_mandas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class Dish {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //o campo id vai ser gerado pelo próprio bd
    private Long id;
    private String name;
    private String description;
    private Category category;
    private BigDecimal price;
    // private Long id_restaurant;
}