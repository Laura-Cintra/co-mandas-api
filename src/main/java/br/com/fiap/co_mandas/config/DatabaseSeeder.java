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
}
