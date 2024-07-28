package main.foodkart.repository;

import main.foodkart.models.FoodDetails;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class FoodRepository {

    private ConcurrentMap<UUID, ConcurrentMap<String, Double>> foodDatabase;

    public FoodRepository() {
        this.foodDatabase = new ConcurrentHashMap<>();
    }

    public void updateFoodMenu(UUID restaurantId, List<FoodDetails> foodDetailsList) {
        if (foodDatabase.containsKey(restaurantId)) {
            ConcurrentMap<String, Double> foodMap = foodDatabase.get(restaurantId);

            List<CompletableFuture<Void>> foodMapUpdateFutures = foodDetailsList.stream()
                    .map(it -> CompletableFuture.runAsync(() -> foodMap.put(it.getFoodName(), it.getPrice()))).toList();

            CompletableFuture.allOf(foodMapUpdateFutures.toArray(new CompletableFuture[0])).join();
        } else {
            throw new RuntimeException("No restaurant is registered with given id" + restaurantId);
        }
    }

    public List<FoodDetails> getFoodMenuOfRestaurant(UUID restaurantId) {
        if (foodDatabase.containsKey(restaurantId)) {
            return foodDatabase.get(restaurantId).entrySet()
                    .stream()
                    .map(entry -> new FoodDetails(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("No restaurant found with given id" + restaurantId);
        }
    }
}
