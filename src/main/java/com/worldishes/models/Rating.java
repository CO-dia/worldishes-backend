package com.worldishes.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "user_id", nullable = false)
    private UUID userId;

    @JoinColumn(name = "dish_id", nullable = false)
    private UUID dishId;

    @Column(nullable = false)
    private Integer stars;

    @Column(length = 500)
    private String comment;

    public Rating(UUID id,
                  UUID dishId,
                  UUID userId,
                  Integer stars,
                  String comment) {
        this.id = id;
        this.dishId = dishId;
        this.userId = userId;
        this.stars = stars;
        this.comment = comment;
    }

    public Rating() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDishId() {
        return dishId;
    }

    public void setDishId(UUID dishId) {
        this.dishId = dishId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(id, rating.id) && Objects.equals(dishId, rating.dishId) && Objects.equals(userId,
                rating.userId) && Objects.equals(stars, rating.stars) && Objects.equals(comment, rating.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dishId, userId, stars, comment);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", userId=" + userId +
                ", stars=" + stars +
                ", comment='" + comment + '\'' +
                '}';
    }
}
