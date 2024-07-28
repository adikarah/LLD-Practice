package main.foodkart.models;

import java.util.List;
import java.util.UUID;

public class Restaurant {

    private UUID restaurantId;

    private List<FoodDetails> foodMenu;

    private int processingCapacity;

    private int orderReceivedCount;

    public Restaurant(List<FoodDetails> foodMenu, int processingCapacity) {
        this.restaurantId = UUID.randomUUID();
        this.foodMenu = foodMenu;
        this.processingCapacity = processingCapacity;
        this.orderReceivedCount = 0;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<FoodDetails> getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(List<FoodDetails> foodMenu) {
        this.foodMenu = foodMenu;
    }

    public int getProcessingCapacity() {
        return processingCapacity;
    }

    public void setProcessingCapacity(int processingCapacity) {
        this.processingCapacity = processingCapacity;
    }

    public int getOrderReceivedCount() {
        return orderReceivedCount;
    }

    public void setOrderReceivedCount(int orderReceivedCount) {
        this.orderReceivedCount = orderReceivedCount;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", foodMenu=" + foodMenu +
                ", processingCapacity=" + processingCapacity +
                ", orderReceivedCount=" + orderReceivedCount +
                '}';
    }
}
