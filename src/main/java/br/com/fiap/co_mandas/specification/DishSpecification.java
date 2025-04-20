package br.com.fiap.co_mandas.specification;

import br.com.fiap.co_mandas.controller.DishController.DishFilters;
import br.com.fiap.co_mandas.model.Dish;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;

public class DishSpecification {

    public static Specification<Dish> withFilters(DishFilters dishFilters){
        return(root, query, cb) -> {
            var predicates = new ArrayList<>();

            if (dishFilters.name() != null) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("name")), "%" + dishFilters.name().toLowerCase() + "%"
                        )
                );
            }

            // busca pratos em uma faixa de preço específica
            if (dishFilters.firstPrice() != null && dishFilters.endPrice() != null) {
                predicates.add(
                        cb.between(root.get("price"), dishFilters.firstPrice(), dishFilters.endPrice())
                );
            }

            // busca pratos de um preço específico
            if(dishFilters.firstPrice() != null && dishFilters.endPrice() == null){
                predicates.add(
                        cb.equal(root.get("price"), dishFilters.firstPrice())
                );
            }

            var arrayPredicates= predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
