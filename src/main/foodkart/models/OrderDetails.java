package main.foodkart.models;

import java.util.List;
import java.util.UUID;

public class OrderDetails {

    private UUID orderId;

    private UUID customerId;

    private UUID restaurantId;

    private List<FoodWithQuantity> orderItems;

    private StatusEnum orderStatus;

    private double orderValue;

    public OrderDetails(UUID customerId, UUID restaurantId, List<FoodWithQuantity> orderItems, double orderValue) {
        this.orderId = UUID.randomUUID();
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderItems = orderItems;
        this.orderStatus = StatusEnum.NOT_DELIVERED;
        this.orderValue = orderValue;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public StatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(StatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }
}

