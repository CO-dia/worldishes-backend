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
    public ResponseEntity<List<DishService.DishResponse>> getAllDishes() {
        return ResponseEntity.ok(dishService.getDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishService.DishResponse> getDishById(@PathVariable UUID id) {
        // TODO: Add implementation to get user's rating on the dish using RatingRepository.findByDishIdAndUserId

        return ResponseEntity.ok(dishService.getDish(id));
    }

    @PostMapping
    private ResponseEntity<DishService.DishResponse> createDish(@RequestBody DishService.DishRequest request) {
        DishService.DishResponse createdDish = dishService.createDishes(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDish);
    }

    @PutMapping("/{id}")
    private ResponseEntity<DishService.DishResponse> updateDish(@PathVariable UUID id, @RequestBody DishService.DishRequest updatedDish) {
        DishService.DishResponse dish = dishService.updateDish(id, updatedDish);
        return ResponseEntity.ok(dish);
    }

    /* Because API unsecured, we don't want to allow deletion of dishes
    @DeleteMapping("/{id}")
    private ResponseEntity.BodyBuilder deleteDish(@PathVariable UUID id) {
        dishService.deleteDish(id);
        return ResponseEntity.ok();
    }*/
}
