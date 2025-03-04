package com.worldishes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DishRepository
        extends JpaRepository<Dish, UUID> {
}
