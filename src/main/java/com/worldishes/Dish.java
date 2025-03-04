package com.worldishes;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Dish {
    @Id
    @UuidGenerator()
    @GeneratedValue(
            strategy = GenerationType.UUID,
            generator = "dish_id_uuid"
    )
    private UUID id;
    private UUID userId;
    private String name;
    private String description;
    private Integer preparationTime;
    private String recipe;
    private String youtubeLink;
    private Double ratingAverage;

    public Dish(UUID id,
                UUID userId,
                String name,
                String description,
                Integer preparationTime,
                String recipe,
                String youtubeLink,
                Double ratingAverage) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.recipe = recipe;
        this.youtubeLink = youtubeLink;
        this.ratingAverage = ratingAverage;
    }

    public Dish() {
    }

    public Double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(Double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) && Objects.equals(userId, dish.userId) && Objects.equals(name, dish.name) && Objects.equals(description, dish.description) && Objects.equals(preparationTime, dish.preparationTime) && Objects.equals(recipe, dish.recipe) && Objects.equals(youtubeLink, dish.youtubeLink) && Objects.equals(ratingAverage, dish.ratingAverage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, description, preparationTime, recipe, youtubeLink, ratingAverage);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", preparationTime=" + preparationTime +
                ", recipe='" + recipe + '\'' +
                ", youtubeLink='" + youtubeLink + '\'' +
                ", ratingAverage=" + ratingAverage +
                '}';
    }
}
