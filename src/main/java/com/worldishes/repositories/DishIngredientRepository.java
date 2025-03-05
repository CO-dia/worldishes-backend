package com.worldishes.repositories;

import com.worldishes.models.DishIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DishIngredientRepository extends JpaRepository<DishIngredient, Long> {

    // Find all ingredients, quantities and unities for a particular dish
    List<DishIngredient> findByDishId(UUID dishId);
}