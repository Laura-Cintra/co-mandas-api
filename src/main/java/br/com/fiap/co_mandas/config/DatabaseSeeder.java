package br.com.fiap.co_mandas.config;

import br.com.fiap.co_mandas.model.Category;
import br.com.fiap.co_mandas.model.Dish;
import br.com.fiap.co_mandas.model.Restaurant;
import br.com.fiap.co_mandas.repository.DishRepository;
import br.com.fiap.co_mandas.repository.RestaurantRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostConstruct
    public void init() {
        seedDishes();
        seedRestaurants();
    }

    private void seedDishes() {
        var names = List.of(
                "Macarrão ao molho branco", "Macarrão à bolonhesa", "Macarrão ao alho e óleo",
                "Macarrão com queijo", "Macarrão com frango", "Pizza de calabresa",
                "Pizza de muçarela", "Pizza marguerita", "Pizza quatro queijos",
                "Pizza de frango com catupiry", "Hambúrguer artesanal", "Hambúrguer de frango",
                "Hambúrguer vegetariano", "Hambúrguer com cheddar", "Hambúrguer duplo",
                "Salada Caesar", "Salada de atum", "Salada tropical", "Salada com frango grelhado",
                "Salada de folhas verdes", "Frango assado com batatas", "Frango grelhado",
                "Frango à parmegiana", "Frango xadrez", "Frango ao curry",
                "Bolo de chocolate", "Bolo de cenoura com cobertura", "Bolo de fubá",
                "Bolo recheado de morango", "Bolo gelado de coco"
        );

        var descriptions = List.of(
                "Delicioso e feito na hora", "Prato especial da casa", "Receita tradicional com um toque moderno",
                "Muito sabor e crocância", "Ideal para compartilhar", "Leve e saudável", "Para quem ama doces",
                "Opção vegetariana", "Clássico que nunca sai de moda", "Feito com ingredientes frescos"
        );

        var categories = Category.values();
        var random = new Random();
        List<Dish> dishes = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            var name = names.get(random.nextInt(names.size()));
            var description = descriptions.get(random.nextInt(descriptions.size()));
            var category = categories[random.nextInt(categories.length)];
            var price = BigDecimal.valueOf(10 + (100 - 10) * random.nextDouble()).setScale(2, BigDecimal.ROUND_HALF_UP);

            dishes.add(Dish.builder()
                    .name(name)
                    .description(description)
                    .category(category)
                    .price(price)
                    .build());
        }

        dishRepository.saveAll(dishes);
    }

    private void seedRestaurants() {
        if (restaurantRepository.count() > 0) return;

        List<String> businessHours = List.of(
                "Segunda-feira: 13:00 - 22:00",
                "Terça-feira: 13:00 - 22:00",
                "Quarta-feira: 13:00 - 22:00",
                "Quinta-feira: 13:00 - 22:00",
                "Sexta-feira: 13:00 - 23:00",
                "Sábado: 12:00 - 23:00",
                "Domingo: 12:00 - 22:00"
        );

        List<Restaurant> restaurants = List.of(
                Restaurant.builder()
                        .name("LE CONSULAT")
                        .description("É um restaurante de comida italiana tradicional e inovadora.")
                        .businessHours(businessHours)
                        .address("Vila Medeiros, zona norte de São Paulo.")
                        .cnpj("46.179.149/0004-23")
                        .build(),

                Restaurant.builder()
                        .name("Sabor do Sertão")
                        .description("Culinária nordestina com muito sabor e tradição.")
                        .businessHours(businessHours)
                        .address("Rua das Palmeiras, 123 - Recife, PE")
                        .cnpj("12.345.678/0001-99")
                        .build(),

                Restaurant.builder()
                        .name("Tokyo Sushi Bar")
                        .description("Sushi moderno com ingredientes frescos e ambiente elegante.")
                        .businessHours(businessHours)
                        .address("Av. Paulista, 900 - São Paulo, SP")
                        .cnpj("98.765.432/0001-11")
                        .build()
        );

        restaurantRepository.saveAll(restaurants);
    }
}
