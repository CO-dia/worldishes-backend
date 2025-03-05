package com.worldishes.services;

import com.worldishes.models.Dish;
import com.worldishes.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }

    public Dish getDish(UUID id) {
        return dishRepository.findById(id).orElse(null);
    }

    public Dish createDishes(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish updateDish(UUID id, Dish updatedDish) {
        return dishRepository.findById(id).map(existingDish -> {
            // Update fields only if they are not null (to avoid overwriting with null)
            if (updatedDish.getName() != null) existingDish.setName(updatedDish.getName());
            if (updatedDish.getDescription() != null) existingDish.setDescription(updatedDish.getDescription());
            if (updatedDish.getCountryCode() != null) existingDish.setCountryCode(updatedDish.getCountryCode());
            if (updatedDish.getPreparationTime() != null)
                existingDish.setPreparationTime(updatedDish.getPreparationTime());
            if (updatedDish.getRecipe() != null) existingDish.setRecipe(updatedDish.getRecipe());
            if (updatedDish.getYoutubeLink() != null) existingDish.setYoutubeLink(updatedDish.getYoutubeLink());
            if (updatedDish.getRatingAverage() != null) existingDish.setRatingAverage(updatedDish.getRatingAverage());

            return dishRepository.save(existingDish);
        }).orElseThrow(() -> new RuntimeException("Dish not found"));
    }

    public void deleteDish(UUID id) {
        dishRepository.findById(id).map(existingDish -> {
            dishRepository.delete(existingDish);
            return existingDish;
        }).orElseThrow(() -> new RuntimeException("Dish not found"));
    }
}
