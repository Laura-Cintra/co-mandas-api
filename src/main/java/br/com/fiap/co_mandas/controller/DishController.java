package  br.com.fiap.co_mandas.controller;

import br.com.fiap.co_mandas.model.Dish;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired // injeção de dependência
    private DishRepository repository;

    @GetMapping()
    public List<Dish> index(){
        return repository.findAll();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Dish create(@RequestBody @Valid Dish dish){
        log.info("Cadastrando o prato " + dish.getName());
        repository.save(dish);
        return dish;
    }

    @GetMapping("{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        log.info("Buscando prato " + id);
        return ResponseEntity.ok(getDish(id));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteDishById(@PathVariable Long id) {
        System.out.println("Deletando prato " + id);
        repository.delete(getDish(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid Dish dish){
        log.info("Atualizando prato" + id);

        getDish(id);
        dish.setId(id);
        repository.save(dish);
        return ResponseEntity.ok(dish);
    }

    private Dish getDish(Long id){
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prato não encontrado"));
    }
}