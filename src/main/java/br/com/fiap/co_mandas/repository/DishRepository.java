package br.com.fiap.co_mandas.repository;

import br.com.fiap.co_mandas.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DishRepository extends JpaRepository<Dish, Long>, JpaSpecificationExecutor<Dish> {

    // todos os métodos do CRUD serão implementados

}
