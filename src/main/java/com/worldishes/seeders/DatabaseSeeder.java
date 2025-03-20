/*
package com.worldishes.seeders;

import com.worldishes.models.*;
import com.worldishes.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final DishIngredientRepository dishIngredientRepository;
    private final RatingRepository ratingRepository;
    private final ImageRepository imageRepository;

    public DatabaseSeeder(UserRepository userRepository, DishRepository dishRepository,
                          DishIngredientRepository dishIngredientRepository, RatingRepository ratingRepository,
                          ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
        this.dishIngredientRepository = dishIngredientRepository;
        this.ratingRepository = ratingRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) { // Only seed if DB is empty
            System.out.println("🌱 Seeding database...");

            Dish dish1;
            Dish dish2;
            Dish dish3;
            Dish dish4;

            // 1️⃣ Seed Users
            List<User> users = List.of(
                    new User("user1@example.com", "Alice", "https://example.com/alice.jpg", "google-1"),
                    new User("user2@example.com", "Bob", "https://example.com/bob.jpg", "google-2"),
                    new User("user3@example.com", "Charlie", "https://example.com/charlie.jpg", "google-3"),
                    new User("user4@example.com", "David", "https://example.com/david.jpg", "google-4"),
                    new User("user5@example.com", "Eve", "https://example.com/eve.jpg", "google-5"),
                    new User("user6@example.com", "Frank", "https://example.com/frank.jpg", "google-6"),
                    new User("user7@example.com", "Grace", "https://example.com/grace.jpg", "google-7"),
                    new User("user8@example.com", "Hank", "https://example.com/hank.jpg", "google-8"),
                    new User("user9@example.com", "Ivy", "https://example.com/ivy.jpg", "google-9"),
                    new User("user10@example.com", "Jack", "https://example.com/jack.jpg", "google-10")
            );
            userRepository.saveAll(users);

            // 2️⃣ Seed Dishes
            List<Dish> dishes = List.of(
                    dish1 = new Dish(users.get(3), "Pasta", "Classic Italian dish", 30, 4, "Boil pasta, add sauce", "IT"
                            , "https://youtube.com/pasta", 4.5, 2, true, "https://worldishes-images-bucket.s3.us-east-2.amazonaws.com/pasta.jpg"),
                    dish2 = new Dish(users.get(1), "Poutine", "Canadian specialty", 20, 2, "Fries, cheese curds, gravy",
                            "CA", "https://youtube.com/poutine", 4.8, 15, false),
                    dish3 = new Dish(users.get(1), "Sushi", "Japanese delicacy", 40, 6, "Rice, fish, roll", "JP",
                            "https" +
                            "://youtube.com/sushi", 4.9, 10, false, "https://worldishes-images-bucket.s3.us-east-2.amazonaws.com/sushi.jpg"),
                    dish4 = new Dish(users.get(7), "Tacos", "Mexican street food", 25, 15, "Tortilla, meat, toppings",
                            "MX", "https://youtube.com/tacos", 4.7, 103, true, "https://worldishes-images-bucket.s3.us-east-2.amazonaws.com/tacos.webp"),
                    new Dish(users.get(4), "Biryani", "Spiced rice dish", 50, 20, "Cook rice, add meat, spices", "IN"
                            , "https://youtube.com/biryani", 4.6, 5, false),
                    new Dish(users.get(8), "Burger", "Classic American", 15, 4, "Grill patty, assemble burger", "US",
                            "https://youtube.com/burger", 3.6, 20, true),
                    new Dish(users.get(2), "Pho", "Vietnamese soup", 45, 2, "Boil broth, add noodles, meat", "VN",
                            "https://youtube.com/pho", 4.8, 7, true),
                    new Dish(users.get(9), "Pad Thai", "Thai noodle dish", 35, 2, "Stir-fry noodles with sauce", "TH"
                            , "https://youtube.com/padthai", 4.7, 12, false),
                    new Dish(users.get(2), "Ratatouille", "French veggie dish", 30, 1, "Chop vegetables, bake", "FR",
                            "https://youtube.com/ratatouille", 4.5, 3, false),
                    new Dish(users.get(6), "Falafel", "Middle Eastern snack", 20, 6, "Fry chickpea balls", "LB",
                            "https://youtube.com/falafel", 2.3, 8, false)
            );
            dishRepository.saveAll(dishes);

            // 3️⃣ Seed Dish Ingredients
            List<DishIngredient> ingredients = List.of(
                    new DishIngredient(dishes.get(0).getId(), "Pasta", 200.0, "g"),
                    new DishIngredient(dishes.get(1).getId(), "Potatoes", 300.0, "g"),
                    new DishIngredient(dishes.get(2).getId(), "Rice", 250.0, "g"),
                    new DishIngredient(dishes.get(3).getId(), "Tortilla", 2.0, "pcs"),
                    new DishIngredient(dishes.get(4).getId(), "Chicken", 300.0, "g"),
                    new DishIngredient(dishes.get(5).getId(), "Beef patty", 150.0, "g"),
                    new DishIngredient(dishes.get(6).getId(), "Noodles", 250.0, "g"),
                    new DishIngredient(dishes.get(7).getId(), "Peanuts", 50.0, "g"),
                    new DishIngredient(dishes.get(8).getId(), "Tomatoes", 100.0, "g"),
                    new DishIngredient(dishes.get(9).getId(), "Chickpeas", 200.0, "g")
            );
            dishIngredientRepository.saveAll(ingredients);

            // 4️⃣ Seed Ratings
            List<Rating> ratings = List.of(
                    new Rating(users.get(0), dishes.get(0).getId(), 5, "Amazing pasta!"),
                    new Rating(users.get(1), dishes.get(1).getId(), 4, "Delicious poutine"),
                    new Rating(users.get(2), dishes.get(2).getId(), 5, "Best sushi ever"),
                    new Rating(users.get(3), dishes.get(3).getId(), 4, "Tacos were great"),
                    new Rating(users.get(4), dishes.get(4).getId(), 5, "Loved the biryani"),
                    new Rating(users.get(5), dishes.get(5).getId(), 3, "Burger was average"),
                    new Rating(users.get(6), dishes.get(6).getId(), 4, "Pho was tasty"),
                    new Rating(users.get(7), dishes.get(7).getId(), 5, "Pad Thai was amazing"),
                    new Rating(users.get(8), dishes.get(8).getId(), 4, "Good ratatouille"),
                    new Rating(users.get(9), dishes.get(9).getId(), 5, "Crispy falafel")
            );
            ratingRepository.saveAll(ratings);

            List<Image> images = List.of(
                    new Image(dish1.getId(), "https://worldishes-images-bucket.s3.us-east-2.amazonaws.com/pasta.jpg",
                            true),
                    new Image(dish2.getId(), "https://worldishes-images-bucket.s3.us-east-2.amazonaws.com/poutine" +
                            ".jpeg", false),
                    new Image(dish3.getId(), "https://worldishes-images-bucket.s3.us-east-2.amazonaws.com/sushi.jpg",
                            true),
                    new Image(dish4.getId(), "https://worldishes-images-bucket.s3.us-east-2.amazonaws.com/tacos.webp"
                            , true)
            );
            imageRepository.saveAll(images);

            System.out.println("✅ Database seeding completed!");
        } else {
            System.out.println("✅ Database already contains data, skipping seeding.");
        }
    }
}
*/
