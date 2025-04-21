package br.com.fiap.co_mandas.repository;

import br.com.fiap.co_mandas.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}