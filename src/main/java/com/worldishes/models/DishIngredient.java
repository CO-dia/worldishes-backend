package com.worldishes.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "dishes_ingredients")
public class DishIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "dish_id", nullable = false)
    private UUID dishId;

    @Column(nullable = false)
    private String ingredient;

    @Column(nullable = false)
    private Double quantity;
    private String unit;

    public DishIngredient(UUID dishId, String ingredient, Double quantity, String unit) {
        this.dishId = dishId;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public DishIngredient() {
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

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DishIngredient that = (DishIngredient) o;
        return Objects.equals(id, that.id) && Objects.equals(dishId, that.dishId) && Objects.equals(ingredient, that.ingredient) && Objects.equals(quantity, that.quantity) && Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dishId, ingredient, quantity, unit);
    }

    @Override
    public String toString() {
        return "DishIngredient{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", ingredient=" + ingredient +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                '}';
    }
}
