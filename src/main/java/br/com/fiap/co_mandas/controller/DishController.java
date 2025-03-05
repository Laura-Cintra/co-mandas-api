package  br.com.fiap.co_mandas.controller;

import br.com.fiap.co_mandas.model.Dish;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DishController {

    private List<Dish> repository = new ArrayList<>();

    @GetMapping("/dishes")
    public List<Dish> index(){
        return repository;
    }

    @PostMapping("/dishes")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Dish create(@RequestBody Dish dish){
        System.out.println("Cadastrando um prato " + dish.getName());
        repository.add(dish);
        return dish;
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        System.out.println("Buscando prato " + id);
        var dish = repository.stream().filter(d -> d.getId().equals(id)).findFirst();

        if (dish.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dish.get());
    }

    @DeleteMapping("/dishes/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteDishById(@PathVariable Long id) {
        System.out.println("Deletando prato " + id);
        repository.removeIf(dish -> dish.getId().equals(id));
    }

}