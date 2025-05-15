package br.com.fiap.co_mandas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.co_mandas.model.Restaurant;
import br.com.fiap.co_mandas.model.User;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByUser(User user);
}