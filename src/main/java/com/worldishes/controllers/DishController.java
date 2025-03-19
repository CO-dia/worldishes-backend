package com.worldishes.controllers;

import com.worldishes.DTO.DishIngredientDTO;
import com.worldishes.models.DishIngredient;
import com.worldishes.models.Image;
import com.worldishes.models.User;
import com.worldishes.services.DishIngredientService;
import com.worldishes.services.DishService;
import com.worldishes.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {
    private final DishService dishService;
    private final DishIngredientService dishIngredientService;
    private final RatingService ratingService;

    public DishController(DishService dishService, DishIngredientService dishIngredientService, RatingService ratingService) {
        this.dishService = dishService;
        this.dishIngredientService = dishIngredientService;
        this.ratingService = ratingService;
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

    @GetMapping("/{id}/ingredients")
    public ResponseEntity<List<DishIngredient>> getDishIngredients(@PathVariable UUID id) {
        return ResponseEntity.ok(dishIngredientService.getIngredientsForDish(id));
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<List<Image>> getDishImages(@PathVariable UUID id) {
        return ResponseEntity.ok(dishService.getDishImages(id));
    }

    @GetMapping("/{id}/rating-user")
    public ResponseEntity<Optional<RatingService.RatingResponse>> getDishRatingByUser(@PathVariable UUID id, @RequestParam UUID userId) {
        return ResponseEntity.ok(ratingService.getRatingByDishAndUser(id, userId));
    }

    @PostMapping
    public ResponseEntity<DishService.DishResponse> createDish(@RequestBody DishService.DishRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        DishService.DishResponse createdDish = dishService.createDishes(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDish);
    }

    @PostMapping("/{id}/ingredients")
    public ResponseEntity<List<DishIngredient>> addIngredient(@PathVariable UUID id, @RequestBody List<DishIngredientDTO> ingredients) {
        // Convert DTO to entity
        List<DishIngredient> dishIngredients = ingredients.stream()
                .map(dto -> new DishIngredient(
                        id,
                        dto.getIngredient(),
                        dto.getQuantity(),
                        dto.getUnit()
                ))
                .toList();

        return ResponseEntity.ok(dishIngredientService.addIngredientToDish(id, dishIngredients));
    }

    @PostMapping("/{id}/images")
    public Image addImage(@PathVariable UUID id, @RequestBody DishService.ImageRequest request) {
        return dishService.addImageToDish(id, request.link(), request.isCover());
    }

    /* Because we don't have a page to update dishes yet, we don't want to allow updating of dishes
    @PutMapping("/{id}")
    public ResponseEntity<DishService.DishResponse> updateDish(@PathVariable UUID id, @RequestBody DishService.DishRequest updatedDish) {
        DishService.DishResponse dish = dishService.updateDish(id, updatedDish);
        return ResponseEntity.ok(dish);
    }*/

    /* Because API unsecured, we don't want to allow deletion of dishes
    @DeleteMapping("/{id}")
    private ResponseEntity.BodyBuilder deleteDish(@PathVariable UUID id) {
        dishService.deleteDish(id);
        return ResponseEntity.ok();
    }*/
}
