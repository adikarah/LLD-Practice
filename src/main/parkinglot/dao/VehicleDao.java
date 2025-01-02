package main.parkinglot.dao;

import main.parkinglot.models.VehicleParkingDetails;
import main.parkinglot.models.enums.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.HashMap;

public class VehicleDao {

    private final HashMap<Vehicle, VehicleParkingDetails> parkedVehicleDatabase;

    public VehicleDao() {
        this.parkedVehicleDatabase = new HashMap<>();
    }

    public VehicleParkingDetails getParkingDetailsByVehicle(Vehicle vehicle) {
        return this.parkedVehicleDatabase.get(vehicle);
    }

    public void enterNewVehicleInParkingSlot(Vehicle vehicle, VehicleParkingDetails vehicleParkingDetails) {
        this.parkedVehicleDatabase.put(vehicle, vehicleParkingDetails);
    }

    public void exitVehicleFromParkingSlot(Vehicle vehicle, LocalDateTime exitTime) {
        var parkedVehicleDetails = this.parkedVehicleDatabase.get(vehicle);
        parkedVehicleDetails.setExitTime(exitTime);
        this.parkedVehicleDatabase.put(vehicle, parkedVehicleDetails);

    }
}
