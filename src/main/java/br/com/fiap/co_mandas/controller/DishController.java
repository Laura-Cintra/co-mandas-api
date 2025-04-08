package  br.com.fiap.co_mandas.controller;

import br.com.fiap.co_mandas.model.Dish;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import br.com.fiap.co_mandas.repository.DishRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@CrossOrigin(origins = "http://localhost:3000")
public class DishController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishRepository repository;

    @GetMapping()
    @Operation(
            summary = "Listar todos os pratos",
            description = "Retorna uma lista com todos os pratos cadastrados no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de pratos retornada com sucesso")
            }
    )
    @Cacheable("dishes")
    public List<Dish> index() {
        return repository.findAll();
    }

    @PostMapping()
    @CacheEvict(value = "dishes", allEntries = true)
    @Operation(
            summary = "Cadastrar um novo prato",
            description = "Cria um novo prato com os dados enviados no corpo da requisição.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Prato criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição")
            }
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    public Dish create(@RequestBody @Valid Dish dish) {
        log.info("Cadastrando o prato " + dish.getName());
        repository.save(dish);
        return dish;
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Buscar um prato por ID",
            description = "Retorna os dados de um prato específico com base no ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Prato encontrado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Prato com o ID especificado não encontrado")
            }
    )
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        log.info("Buscando prato " + id);
        return ResponseEntity.ok(getDish(id));
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Excluir um prato",
            description = "Remove um prato com base no ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Prato removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Prato com o ID especificado não encontrado")
            }
    )
    @CacheEvict(value = "dishes", allEntries = true)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteDishById(@PathVariable Long id) {
        log.info("Deletando prato " + id);
        repository.delete(getDish(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Atualizar um prato existente",
            description = "Atualiza os dados de um prato com base no ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Prato atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
                    @ApiResponse(responseCode = "404", description = "Prato com o ID especificado não encontrado")
            }
    )
    @CacheEvict(value = "dishes", allEntries = true)
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid Dish dish) {
        log.info("Atualizando prato " + id);
        getDish(id);
        dish.setId(id);
        repository.save(dish);
        return ResponseEntity.ok(dish);
    }

    private Dish getDish(Long id){
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Prato não encontrado"));
    }
}
