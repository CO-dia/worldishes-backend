package com.worldishes.services;

import com.worldishes.models.DishIngredient;
import com.worldishes.repositories.DishIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DishIngredientService {

    private final DishIngredientRepository dishIngredientRepository;

    public DishIngredientService(DishIngredientRepository dishIngredientRepository) {
        this.dishIngredientRepository = dishIngredientRepository;
    }

    // Get all ingredients for a specific dish
    public List<DishIngredient> getIngredientsForDish(UUID dishId) {
        return dishIngredientRepository.findByDishId(dishId);
    }

    // Add a new ingredient to a dish
    public List<DishIngredient> addIngredientToDish(UUID dishId, List<DishIngredient> ingredients) {
        for (DishIngredient ingredient : ingredients) {
            DishIngredient dishIngredient = new DishIngredient();
            dishIngredient.setDishId(dishId);
            dishIngredient.setIngredient(ingredient.getIngredient());
            dishIngredient.setQuantity(ingredient.getQuantity());
            dishIngredient.setUnit(ingredient.getUnit());
            dishIngredientRepository.save(dishIngredient);
        }

        return ingredients;
    }
}