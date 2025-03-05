package com.worldishes.services;

import com.worldishes.models.Dish;
import com.worldishes.models.Ingredient;
import com.worldishes.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredient(UUID id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    // Todo: Create a function to search an ingredient by name
}
