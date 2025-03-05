package com.worldishes.controllers;

import com.worldishes.models.Dish;
import com.worldishes.models.Ingredient;
import com.worldishes.services.DishService;
import com.worldishes.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<Ingredient> getIngredients() {
        return ingredientService.getIngredients();
    }

    @GetMapping("/{ingredientId}")
    public Ingredient getIngredient(@PathVariable("ingredientId") UUID id) {
        return ingredientService.getIngredient(id);
    }
}
