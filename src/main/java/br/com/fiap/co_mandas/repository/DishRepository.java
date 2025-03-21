package br.com.fiap.co_mandas.repository;

import br.com.fiap.co_mandas.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {

    // todos os métodos do CRUD serão implementados

}
