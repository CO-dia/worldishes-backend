package com.worldishes.services;

import com.worldishes.DTO.UserDTO;
import com.worldishes.models.Dish;
import com.worldishes.models.Image;
import com.worldishes.models.User;
import com.worldishes.repositories.DishRepository;
import com.worldishes.repositories.ImageRepository;
import com.worldishes.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public DishService(DishRepository dishRepository, UserRepository userRepository, ImageRepository imageRepository) {
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
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

    public List<Image> getDishImages(UUID dishId) {
        return imageRepository.findImagesByDishId(dishId);
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
        dish.setServings(dishRequest.servings());
        dish.setRecipe(dishRequest.recipe());
        dish.setCountryCode(dishRequest.countryCode());
        dish.setYoutubeLink(dishRequest.youtubeLink());
        dish.setAnonymous(dishRequest.anonymous());
        dish.setUser(user);

        // Save dish and return response DTO
        Dish savedDish = dishRepository.save(dish);
        return convertToResponse(savedDish);
    }

    public Image addImageToDish(UUID dishId, String link, boolean isCover) {
        // Find the dish by ID
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new RuntimeException("Dish not found"));

        Image image = new Image(dishId, link, isCover);
        imageRepository.save(image);

        if (isCover) {
            dish.setCoverImageUrl(image.getLink());
            dishRepository.save(dish);
        }

        return image;
    }

    public DishResponse updateDish(UUID id, DishRequest dishRequest) {
        // Find existing dish
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));

        // Update fields if provided in the request
        if (dishRequest.name() != null) dish.setName(dishRequest.name());
        if (dishRequest.description() != null) dish.setDescription(dishRequest.description());
        if (dishRequest.preparationTime() != null) dish.setPreparationTime(dishRequest.preparationTime());
        if (dishRequest.servings() != null) dish.setServings(dishRequest.servings());
        if (dishRequest.recipe() != null) dish.setRecipe(dishRequest.recipe());
        if (dishRequest.countryCode() != null) dish.setCountryCode(dishRequest.countryCode());
        if (dishRequest.youtubeLink() != null) dish.setYoutubeLink(dishRequest.youtubeLink());
        if (dishRequest.anonymous() != null) dish.setAnonymous(dishRequest.anonymous());

        dishRepository.save(dish);
        return convertToResponse(dish);
    }

    // Convert Dish entity to DishResponse
    private DishResponse convertToResponse(Dish dish) {
        return new DishResponse(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPreparationTime(),
                dish.getServings(),
                dish.getRecipe(),
                dish.getCountryCode(),
                dish.getCoverImageUrl(),
                dish.getYoutubeLink(),
                dish.getRatingAverage(),
                dish.getRatingCount(),
                dish.getAnonymous(),
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
            Integer servings,
            String recipe,
            String countryCode,
            String coverImageUrl,
            String youtubeLink,
            Double ratingAverage,
            Integer ratingCount,
            Boolean anonymous,
            UserDTO user
    ) {
    }

    // To wait only for the userId instead of full user object
    public record DishRequest(
            String name,
            String description,
            Integer preparationTime,
            Integer servings,
            String recipe,
            String countryCode,
            String youtubeLink,
            Boolean anonymous,
            UUID userId
    ) {
    }

    public record ImageRequest(
            UUID dishId,
            String link,
            boolean isCover
    ) {
    }
}
