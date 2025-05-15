package br.com.fiap.co_mandas.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.co_mandas.model.Restaurant;
import br.com.fiap.co_mandas.model.User;
import br.com.fiap.co_mandas.repository.RestaurantRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class RestaurantController {

    @Autowired
    private RestaurantRepository repository;

    @GetMapping
    @Cacheable("restaurant")
    @Operation(summary = "Listar todos os restaurantes", description = "Retorna uma lista com todos os restaurantes cadastrados no sistema.", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de restaurantes retornada com sucesso")
    })
    public List<Restaurant> index(@AuthenticationPrincipal User user) {
        return repository.findByUser(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um restaurante por ID", description = "Retorna os dados de um restaurante específico com base no ID fornecido.", responses = {
            @ApiResponse(responseCode = "200", description = "Restaurante encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante com o ID especificado não encontrado")
    })
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(getRestaurant(id, user));
    }

    @PostMapping
    @CacheEvict(value = "restaurant", allEntries = true)
    @Operation(summary = "Cadastrar um novo restaurante", description = "Cria um novo restaurante com os dados enviados no corpo da requisição.", responses = {
            @ApiResponse(responseCode = "201", description = "Restaurante criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public Restaurant create(@RequestBody Restaurant restaurant, @AuthenticationPrincipal User user) {
        log.info("Cadastrando restaurante: ", restaurant.getName());
        restaurant.setUser(user);
        return repository.save(restaurant);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "restaurant", allEntries = true)
    public ResponseEntity<Restaurant> update(@PathVariable Long id, @RequestBody Restaurant restaurant,
            @AuthenticationPrincipal User user) {
        log.info("Atualizando restaurante ", id);
        var oldRestaurant = getRestaurant(id, user);
        BeanUtils.copyProperties(restaurant, oldRestaurant, "id", "user");
        repository.save(oldRestaurant);
        return ResponseEntity.ok(oldRestaurant);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "restaurant", allEntries = true)
    public ResponseEntity<Object> destroy(@PathVariable Long id, @AuthenticationPrincipal User user) {
        log.info("Deletando restaurante ", id);
        repository.delete(getRestaurant(id, user));
        return ResponseEntity.noContent().build();
    }

    private Restaurant getRestaurant(Long id, User user) {
        var restaurant = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurante não encontrado"));
        if (!restaurant.getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }
        return restaurant;
    }

}
