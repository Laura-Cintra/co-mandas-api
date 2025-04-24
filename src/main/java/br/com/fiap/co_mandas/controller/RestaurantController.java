package br.com.fiap.co_mandas.controller;

import br.com.fiap.co_mandas.model.Restaurant;
import br.com.fiap.co_mandas.repository.RestaurantRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository repository;

    @GetMapping
    @Cacheable("restaurant")
        @Operation(
            summary = "Listar todos os restaurantes",
            description = "Retorna uma lista com todos os restaurantes cadastrados no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de restaurantes retornada com sucesso")
            }
    )
    public List<Restaurant> index() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar um restaurante por ID",
        description = "Retorna os dados de um restaurante específico com base no ID fornecido.",
        responses = {
                @ApiResponse(responseCode = "200", description = "Restaurante encontrado com sucesso"),
                @ApiResponse(responseCode = "404", description = "Restaurante com o ID especificado não encontrado")
        }
    )
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    }

    @PostMapping
    @CacheEvict(value = "restaurant", allEntries = true)
    @Operation(
        summary = "Cadastrar um novo restaurante",
        description = "Cria um novo restaurante com os dados enviados no corpo da requisição.",
        responses = {
                @ApiResponse(responseCode = "201", description = "Restaurante criado com sucesso"),
                @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição")
        }
)
    public Restaurant create(@RequestBody Restaurant restaurante) {
        return repository.save(restaurante);
    }

}
