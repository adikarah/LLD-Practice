package main.foodkart.repository;

import main.foodkart.models.FoodWithQuantity;
import main.foodkart.models.OrderDetails;
import main.foodkart.models.Restaurant;
import main.foodkart.models.StatusEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

public class OrderRepository {

    private HashMap<UUID, OrderDetails> orderDatabase;
    private RestaurantRepository restaurantRepository;

    public OrderRepository(RestaurantRepository restaurantRepository) {
        this.orderDatabase = new HashMap<>();
        this.restaurantRepository = restaurantRepository;
    }

    public OrderDetails placeOrder(UUID customerId, UUID restaurantId, List<FoodWithQuantity> orderItems) throws ExecutionException, InterruptedException {
        Restaurant restaurant = restaurantRepository.getRestaurantDetailsById(restaurantId);
        double orderValue = getOrderValue(restaurantId, orderItems);
        OrderDetails orderDetails = new OrderDetails(customerId, restaurantId, orderItems, orderValue);
        restaurant.setOrderReceivedCount(restaurant.getOrderReceivedCount() + 1);
        orderDatabase.put(orderDetails.getOrderId(), orderDetails);
        return orderDetails;
    }


    private double getOrderValue(UUID restaurantId, List<FoodWithQuantity> orderItems) throws ExecutionException, InterruptedException {
        AtomicReference<Double> orderValue = new AtomicReference<>(0.0);
        Map<String, Double> foodItemsDetails = restaurantRepository.getFoodPriceByRestaurantId(restaurantId);
        List<CompletableFuture<Void>> priceFutures = orderItems.stream()
                .map(it -> CompletableFuture.runAsync(() -> orderValue.updateAndGet(v -> v + foodItemsDetails.get(it.foodName()))))
                .toList();

        CompletableFuture.allOf(priceFutures.toArray(new CompletableFuture[0])).get();
        return orderValue.get();
    }

    public OrderDetails getOrderDetailsById(UUID orderId) {
        if (orderDatabase.containsKey(orderId)) {
            return orderDatabase.get(orderId);
        } else {
            throw new RuntimeException("No order found for id: " + orderId);
        }
    }

    public void deliverOrder(UUID orderId) {
        OrderDetails orderDetails = getOrderDetailsById(orderId);
        orderDetails.setOrderStatus(StatusEnum.DELIVERED);
        Restaurant restaurant = restaurantRepository.getRestaurantDetailsById(orderDetails.getRestaurantId());
        restaurant.setOrderReceivedCount(restaurant.getOrderReceivedCount() - 1);
    }
}
