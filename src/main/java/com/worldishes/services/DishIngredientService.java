package com.worldishes.services;

import com.worldishes.models.DishIngredient;
import com.worldishes.repositories.DishIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DishIngredientService {

    @Autowired
    private DishIngredientRepository dishIngredientRepository;

    // Get all ingredients for a specific dish
    public List<DishIngredient> getIngredientsForDish(UUID dishId) {
        return dishIngredientRepository.findByDishId(dishId);
    }

    // Add a new ingredient to a dish
    public DishIngredient addIngredientToDish(UUID dishId, String ingredient, Double quantity, String unit) {
        DishIngredient dishIngredient = new DishIngredient();
        dishIngredient.setDishId(dishId);
        dishIngredient.setIngredient(ingredient);
        dishIngredient.setQuantity(quantity);
        dishIngredient.setUnit(unit);
        return dishIngredientRepository.save(dishIngredient);
    }
}