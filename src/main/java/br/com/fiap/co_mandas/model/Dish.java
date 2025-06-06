package br.com.fiap.co_mandas.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // o campo id vai ser gerado pelo próprio bd
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

    @NotNull(message = "Campo obrigatório")
    @ManyToOne
    private Restaurant restaurant;
}