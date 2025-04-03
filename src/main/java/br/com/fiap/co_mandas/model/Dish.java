package br.com.fiap.co_mandas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class Dish {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //o campo id vai ser gerado pelo próprio bd
    private Long id;
    @NotBlank(message = "Não pode estar em branco")
    @Size(min = 5, message = "O nome do prato deve ter mais de 5 caracteres")
    private String name;

    @NotBlank(message = "Não pode estar em branco")
    @Size(min = 10, message = "A descrição deve ter mais de 10 caracteres")
    private String description;

    private Category category;

    @NotNull(message = "O preço não pode ser nulo")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    private BigDecimal price;
    // private Long id_restaurant;
}