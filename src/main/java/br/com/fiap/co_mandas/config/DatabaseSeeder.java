package br.com.fiap.co_mandas.config;

import br.com.fiap.co_mandas.model.Category;
import br.com.fiap.co_mandas.model.Dish;
import br.com.fiap.co_mandas.repository.DishRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DatabaseSeeder {

    @Autowired
    private DishRepository dishRepository;

    @PostConstruct
    public void init() {
        dishRepository.saveAll(List.of(
                Dish.builder()
                        .name("Bolo de chocolate")
                        .description("Bolo molhadinho com cobertura de brigadeiro")
                        .category(Category.SOBREMESA)
                        .price(new BigDecimal("22.35"))
                        .build(),
                Dish.builder()
                        .name("Filé à parmegiana")
                        .description("Filé empanado com queijo e molho de tomate, servido com arroz e batata")
                        .category(Category.PRATO_PRINCIPAL)
                        .price(new BigDecimal("32.50"))
                        .build(),
                Dish.builder()
                        .name("Bruschetta")
                        .description("Pão italiano tostado com tomate, azeite e manjericão")
                        .category(Category.ENTRADA)
                        .price(new BigDecimal("14.90"))
                        .build()
        ));
    }
}
