package com.worldishes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1/dishes")
public class Main {
    private final DishRepository dishRepository;

    public Main(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }

    record NewDishRequest(
            UUID userId,
            String name,
            String description,
            Integer preparationTime,
            String recipe,
            String youtubeLink,
            Double ratingAverage
    ) {

    }

    @PostMapping
    private void addDish(@RequestBody NewDishRequest request) {
        Dish dish = new Dish();
        dish.setUserId(request.userId());
        dish.setName(request.name());
        dish.setDescription(request.description());
        dish.setPreparationTime(request.preparationTime());
        dish.setRecipe(request.recipe());
        dish.setYoutubeLink(request.youtubeLink());
        dish.setRatingAverage(request.ratingAverage());
        dishRepository.save(dish);
    }

    @DeleteMapping("{dishId}")
    private void deleteDish(@PathVariable("dishId") UUID id) {
        dishRepository.deleteById(id);
    }
}
