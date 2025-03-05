package  br.com.fiap.co_mandas.controller;

import br.com.fiap.co_mandas.model.Dish;
import org.springframework.http.HttpStatus;
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

}