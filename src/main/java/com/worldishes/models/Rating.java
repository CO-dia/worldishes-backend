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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JoinColumn(name = "dish_id", nullable = false)
    private UUID dishId;

    @Column(nullable = false)
    private Integer stars;

    @Column(length = 500)
    private String comment;

    public Rating(UUID id,
                  User user,
                  UUID dishId,
                  Integer stars,
                  String comment) {
        this.id = id;
        this.dishId = dishId;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return Objects.equals(id, rating.id) && Objects.equals(dishId, rating.dishId) && Objects.equals(user,
                rating.user) && Objects.equals(stars, rating.stars) && Objects.equals(comment, rating.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dishId, user, stars, comment);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", userId=" + user +
                ", stars=" + stars +
                ", comment='" + comment + '\'' +
                '}';
    }
}
