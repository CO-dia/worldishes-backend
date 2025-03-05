package com.worldishes.controllers;

import com.worldishes.models.Dish;
import com.worldishes.services.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<Dish> getDishes() {
        return dishService.getDishes();
    }

    @GetMapping("/{dishId}")
    public Dish getDish(@PathVariable("dishId") UUID id) {
        // TODO: Add implementation to get user's rating on the dish using RatingRepository.findByDishIdAndUserId

        return dishService.getDish(id);
    }

    @PostMapping
    private ResponseEntity<Dish> createDish(@RequestBody Dish request) {
        Dish createdDish = dishService.createDishes(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDish);
    }

    @PutMapping("/{dishId}")
    private ResponseEntity<Dish> updateDish(@PathVariable("dishId") UUID id, @RequestBody Dish request) {
        Dish dish = dishService.updateDish(id, request);
        return ResponseEntity.ok(dish);
    }

    @DeleteMapping("/{dishId}")
    private ResponseEntity.BodyBuilder deleteDish(@PathVariable("dishId") UUID id) {
        dishService.deleteDish(id);
        return ResponseEntity.ok();
    }
}
