package main.parkinglot.services;

import main.parkinglot.models.enums.Vehicle;
import main.parkinglot.models.enums.VehicleParkingRate;
import main.parkinglot.models.enums.VehicleType;

import java.time.Duration;

public class PaymentService {

    public void generateBill(Vehicle vehicle, Duration duration) {
        System.out.println("Duration is " + duration.toMillis() + " for vehicle " + vehicle.vehicleNo());
        double totalCost = rateList(vehicle.vehicleType()) * duration.toMillis();
        System.out.println("Bill has been generated for vehicle " + vehicle.vehicleNo() + " of cost: " + totalCost);
    }

    public double rateList(VehicleType type) {
        switch (type) {
            case BIKE: {
                return VehicleParkingRate.BIKE.getCost();
            }

            case CAR: {
                return VehicleParkingRate.CAR.getCost();
            }

            case TRUCK: {
                return VehicleParkingRate.TRUCK.getCost();
            }

            default:
                return 0.0;
        }
    }

}
