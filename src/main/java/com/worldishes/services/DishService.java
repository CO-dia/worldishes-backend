package com.worldishes.services;

import com.worldishes.DTO.UserDTO;
import com.worldishes.models.Dish;
import com.worldishes.models.User;
import com.worldishes.repositories.DishRepository;
import com.worldishes.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final UserRepository userRepository;

    public DishService(DishRepository dishRepository, UserRepository userRepository) {
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
    }

    public List<DishResponse> getDishes() {
        return dishRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public DishResponse getDish(UUID id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
        return convertToResponse(dish);
    }

    public DishResponse createDishes(DishRequest dishRequest) {
        // Find the user by ID
        User user = userRepository.findById(dishRequest.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a new Dish entity
        Dish dish = new Dish();
        dish.setName(dishRequest.name());
        dish.setDescription(dishRequest.description());
        dish.setPreparationTime(dishRequest.preparationTime());
        dish.setRecipe(dishRequest.recipe());
        dish.setCountryCode(dishRequest.countryCode());
        dish.setYoutubeLink(dishRequest.youtubeLink());
        dish.setUser(user);

        // Save dish and return response DTO
        Dish savedDish = dishRepository.save(dish);
        return convertToResponse(savedDish);
    }

    public DishResponse updateDish(UUID id, DishRequest dishRequest) {
        // Find existing dish
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));

        // Update fields if provided in the request
        if (dishRequest.name() != null) dish.setName(dishRequest.name());
        if (dishRequest.description() != null) dish.setDescription(dishRequest.description());
        if (dishRequest.preparationTime() != null) dish.setPreparationTime(dishRequest.preparationTime());
        if (dishRequest.recipe() != null) dish.setRecipe(dishRequest.recipe());
        if (dishRequest.countryCode() != null) dish.setCountryCode(dishRequest.countryCode());
        if (dishRequest.youtubeLink() != null) dish.setYoutubeLink(dishRequest.youtubeLink());

        dishRepository.save(dish);
        return convertToResponse(dish);
    }

    public void deleteDish(UUID id) {
        dishRepository.findById(id).map(existingDish -> {
            dishRepository.delete(existingDish);
            return existingDish;
        }).orElseThrow(() -> new RuntimeException("Dish not found"));
    }

    // Convert Dish entity to DishResponse
    private DishResponse convertToResponse(Dish dish) {
        return new DishResponse(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPreparationTime(),
                dish.getRecipe(),
                dish.getCountryCode(),
                dish.getYoutubeLink(),
                dish.getRatingAverage(),
                new UserDTO(
                        dish.getUser().getName(),
                        dish.getUser().getProfileImageUrl()
                )
        );
    }

    // Record used for returning dish data
    public record DishResponse(
            UUID id,
            String name,
            String description,
            Integer preparationTime,
            String recipe,
            String countryCode,
            String youtubeLink,
            Double ratingAverage,
            UserDTO user
    ) {
    }

    // To wait only for the userId instead of full user object
    public record DishRequest(
            String name,
            String description,
            Integer preparationTime,
            String recipe,
            String countryCode,
            String youtubeLink,
            UUID userId
    ) {
    }
}
