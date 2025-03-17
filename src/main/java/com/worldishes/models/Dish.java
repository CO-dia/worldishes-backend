package com.worldishes.models;

import jakarta.persistence.*;

import java.util.List;
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
    private Integer servings;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String recipe;

    @Column(length = 3)
    private String countryCode;
    private String youtubeLink;
    private Double ratingAverage;
    private Integer ratingCount;
    private Boolean anonymous;
    private String coverImageUrl;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /* TODO: Verify if really needed or if it's easier to get this list from DishIngredientRepository
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DishIngredient> dishIngredients;  // List of ingredients for this dish*/

    // Constructor without coverImage (optional)
    public Dish(
            User user,
            String name,
            String description,
            Integer preparationTime,
            Integer servings,
            String recipe,
            String countryCode,
            String youtubeLink,
            Double ratingAverage,
            Integer ratingCount,
            Boolean anonymous) {
        this(user, name, description, preparationTime, servings, recipe, countryCode, youtubeLink, ratingAverage, ratingCount, anonymous, null);
    }

    // Constructor with coverImage
    public Dish(
            User user,
            String name,
            String description,
            Integer preparationTime,
            Integer servings,
            String recipe,
            String countryCode,
            String youtubeLink,
            Double ratingAverage,
            Integer ratingCount,
            Boolean anonymous,
            String coverImageUrl) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.preparationTime = preparationTime;
        this.servings = servings;
        this.recipe = recipe;
        this.countryCode = countryCode;
        this.youtubeLink = youtubeLink;
        this.ratingAverage = ratingAverage;
        this.ratingCount = ratingCount;
        this.anonymous = anonymous;
        this.coverImageUrl = coverImageUrl;
    }

    public Dish() {}

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
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
        return Objects.equals(id, dish.id) && Objects.equals(name, dish.name) && Objects.equals(description, dish.description) && Objects.equals(preparationTime, dish.preparationTime) && Objects.equals(servings, dish.servings) && Objects.equals(recipe, dish.recipe) && Objects.equals(countryCode, dish.countryCode) && Objects.equals(youtubeLink, dish.youtubeLink) && Objects.equals(ratingAverage, dish.ratingAverage) && Objects.equals(ratingCount, dish.ratingCount) && Objects.equals(anonymous, dish.anonymous) && Objects.equals(coverImageUrl, dish.coverImageUrl) && Objects.equals(images, dish.images) && Objects.equals(user, dish.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, preparationTime, servings, recipe, countryCode, youtubeLink, ratingAverage, ratingCount, anonymous, coverImageUrl, images, user);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", preparationTime=" + preparationTime +
                ", servings=" + servings +
                ", recipe='" + recipe + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", youtubeLink='" + youtubeLink + '\'' +
                ", ratingAverage=" + ratingAverage +
                ", ratingCount=" + ratingCount +
                ", anonymous=" + anonymous +
                ", coverImageUrl=" + coverImageUrl +
                ", images=" + images +
                ", user=" + user +
                '}';
    }
}
