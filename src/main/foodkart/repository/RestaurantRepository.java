package main.foodkart.repository;

import main.foodkart.models.FoodDetails;
import main.foodkart.models.Restaurant;

import java.util.*;
import java.util.stream.Collectors;

public class RestaurantRepository {

    private HashMap<UUID, Restaurant> restaurantDatabase;

    private FoodRepository foodRepository;


    public RestaurantRepository(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
        this.restaurantDatabase = new HashMap<>();
    }

    public Restaurant addRestaurant(List<FoodDetails> foodDetails, int capacity) {
        Restaurant restaurant = new Restaurant(foodDetails, capacity);
        restaurantDatabase.put(restaurant.getRestaurantId(), restaurant);
        return restaurant;
    }

    public Optional<Restaurant> updateRestaurantMenu(UUID restaurantId, List<FoodDetails> foodItems) {
        if (restaurantDatabase.containsKey(restaurantId)) {
            foodRepository.updateFoodMenu(restaurantId, foodItems);
            Restaurant restaurant = getRestaurantDetailsById(restaurantId);
            restaurant.setFoodMenu(foodRepository.getFoodMenuOfRestaurant(restaurantId));
            restaurantDatabase.put(restaurantId, restaurant);
            return Optional.of(restaurant);
        } else {
            throw new RuntimeException("No restaurant is registered with given id" + restaurantId);
        }
    }

    public Restaurant getRestaurantDetailsById(UUID restaurantId) {
        if (restaurantDatabase.containsKey(restaurantId)) {
            return restaurantDatabase.get(restaurantId);
        } else {
            throw new RuntimeException("No restaurant found with given id" + restaurantId);
        }
    }

    public List<Restaurant> getAllRestaurantDetails() {
        return restaurantDatabase.values().stream().toList();
    }

    public Map<String, Double> getFoodPriceByRestaurantId(UUID restaurantId) {
        if (restaurantDatabase.containsKey(restaurantId)) {
            return restaurantDatabase.get(restaurantId)
                    .getFoodMenu()
                    .stream()
                    .collect(Collectors.toMap(FoodDetails::getFoodName, FoodDetails::getPrice));
        } else {
            throw new RuntimeException("No restaurant found with given id" + restaurantId);
        }
    }

    public List<Restaurant> getRestaurantWithLowestPrice(String foodName) {
        List<Restaurant> restaurantsWithRequestedItem = restaurantDatabase.values()
                .stream()
                .filter(restaurant -> restaurant.getFoodMenu().stream()
                        .anyMatch(food -> food.getFoodName().equalsIgnoreCase(foodName))).toList();

        return restaurantsWithRequestedItem.stream()
                .sorted(Comparator.comparingDouble(restaurant -> restaurant.getFoodMenu().stream()
                        .filter(food -> food.getFoodName().equalsIgnoreCase(foodName))
                        .mapToDouble(FoodDetails::getPrice)
                        .min()
                        .orElse(Double.MAX_VALUE)))
                .collect(Collectors.toList());
    }

}
