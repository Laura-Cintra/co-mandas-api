package br.com.fiap.co_mandas.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do restaurante não pode estar em branco")
    private String name;

    @NotBlank(message = "A descrição não pode estar em branco")
    @Size(min = 10, message = "A descrição deve ter pelo menos 10 caracteres")
    private String description;

    @ElementCollection
    @CollectionTable(name = "business_hours", joinColumns = @JoinColumn(name = "restaurant_id"))
    private List<String> businessHours;

    @NotBlank(message = "O endereço não pode estar em branco")
    private String address;

    @NotBlank(message = "O CNPJ não pode estar em branco")
    @Pattern(
        regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}",
        message = "O CNPJ deve estar no formato 00.000.000/0000-00"
    )
    private String cnpj;

}