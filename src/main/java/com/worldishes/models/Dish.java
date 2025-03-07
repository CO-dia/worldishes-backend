package com.worldishes.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 500)
    private String description;

    private Integer preparationTime;

    @Column(length = 1000)
    private String recipe;

    @Column(length = 3)
    private String countryCode;
    private String youtubeLink;
    private Double ratingAverage;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /* TODO: Verify if really needed or if it's easier to get this list from DishIngredientRepository
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DishIngredient> dishIngredients;  // List of ingredients for this dish*/

    public Dish(
                User user,
                String name,
                String description,
                Integer preparationTime,
                String recipe,
                String countryCode,
                String youtubeLink,
                Double ratingAverage) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.recipe = recipe;
        this.countryCode = countryCode;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return Objects.equals(id, dish.id) && Objects.equals(user, dish.user) && Objects.equals(name, dish.name) && Objects.equals(description, dish.description) && Objects.equals(preparationTime, dish.preparationTime) && Objects.equals(countryCode, dish.countryCode) && Objects.equals(recipe, dish.recipe) && Objects.equals(youtubeLink, dish.youtubeLink) && Objects.equals(ratingAverage, dish.ratingAverage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, description, preparationTime, recipe, countryCode, youtubeLink, ratingAverage);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", preparationTime=" + preparationTime +
                ", recipe='" + recipe + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", youtubeLink='" + youtubeLink + '\'' +
                ", ratingAverage=" + ratingAverage +
                '}';
    }
}
