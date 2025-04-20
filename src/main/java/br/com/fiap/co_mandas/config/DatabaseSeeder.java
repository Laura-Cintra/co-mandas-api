package br.com.fiap.co_mandas.config;

import br.com.fiap.co_mandas.model.Category;
import br.com.fiap.co_mandas.model.Dish;
import br.com.fiap.co_mandas.repository.DishRepository;
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

    @PostConstruct
    public void init() {
        var names = List.of(
                "Bolo de chocolate", "Filé à parmegiana", "Bruschetta", "Lasanha", "Tiramisu",
                "Salada Caesar", "Pizza Margherita", "Hambúrguer artesanal", "Risoto de cogumelos",
                "Tacos mexicanos", "Sushi", "Coxinha", "Pão de alho", "Espaguete à bolonhesa",
                "Feijoada", "Moqueca", "Panqueca de carne", "Sorvete artesanal", "Creme brûlée", "Quiche Lorraine"
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
}
