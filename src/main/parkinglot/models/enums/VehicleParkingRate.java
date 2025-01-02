package main.parkinglot.models.enums;

public enum VehicleParkingRate {

    BIKE(200),
    CAR(500),
    TRUCK(800);

    private final int cost;

    VehicleParkingRate(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return this.cost;
    }
}
