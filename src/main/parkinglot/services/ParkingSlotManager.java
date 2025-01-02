package main.parkinglot.services;

import main.parkinglot.dao.ParkingLotDao;
import main.parkinglot.dao.VehicleDao;
import main.parkinglot.models.ParkingSlot;
import main.parkinglot.models.VehicleParkingDetails;
import main.parkinglot.models.enums.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingSlotManager {

    private final ParkingLotDao parkingLotDao;
    private final VehicleDao vehicleDao;

    private final PaymentService paymentService;

    public ParkingSlotManager(ParkingLotDao parkingLotDao, VehicleDao vehicleDao, PaymentService paymentService) {
        this.parkingLotDao = parkingLotDao;
        this.vehicleDao = vehicleDao;
        this.paymentService = paymentService;
    }

    public UUID checkForAvailableSlot(int floorNumber, Vehicle vehicle) {
        for (ParkingSlot parkingSlot : parkingLotDao.getAllParkingSlot(floorNumber)) {
            if (parkingSlot.getVehicleType().equals(vehicle.vehicleType()) && !parkingSlot.isOccupied()) {
                return parkingSlot.getSlotId();
            }
        }
        return null;
    }

    public void parkNewVehicle(int floorNumber, Vehicle vehicle) {
        var availableSlotId = checkForAvailableSlot(floorNumber, vehicle);

        System.out.println("Available slot Id " + availableSlotId + " for vehicle " + vehicle.vehicleNo());

        if (availableSlotId != null) {
            var parkingSlot = parkingLotDao.getParkingSlotDetailsBySlotId(availableSlotId);
            parkingSlot.parkVehicle();
            vehicleDao.enterNewVehicleInParkingSlot(vehicle, new VehicleParkingDetails(availableSlotId, LocalDateTime.now()));

        } else {
            System.out.println("No slot available for now at floorNumber: " + floorNumber + " for vehicle of type: " + vehicle.vehicleType());
        }
    }

    public void exitVehicle(Vehicle vehicle) {
        var vehicleParkingDetails = vehicleDao.getParkingDetailsByVehicle(vehicle);
        if (vehicleParkingDetails != null) {
            var parkingSlot = parkingLotDao.getParkingSlotDetailsBySlotId(vehicleParkingDetails.getSlotNumber());

            if (parkingSlot != null) {
                var exitTime = LocalDateTime.now().plusSeconds(1L);
                parkingSlot.removeVehicle();
                vehicleDao.exitVehicleFromParkingSlot(vehicle, exitTime);
                System.out.println("VehicleParkingDetails: " + vehicleParkingDetails);
                Duration duration = Duration.between(vehicleParkingDetails.getEntryTime(), exitTime);
                paymentService.generateBill(vehicle, duration);
            }
        } else {
            System.out.println("No details found for the provided vehicle: " + vehicle);
        }
    }
}
