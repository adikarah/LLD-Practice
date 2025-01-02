package main.parkinglot.models;

import main.parkinglot.models.enums.VehicleType;

import java.util.UUID;

public class ParkingSlot {

    private final UUID slotId;
    private final VehicleType vehicleType;
    private boolean isOccupied;

    public ParkingSlot(VehicleType vehicleType, boolean isOccupied) {
        this.slotId = UUID.randomUUID();
        this.vehicleType = vehicleType;
        this.isOccupied = isOccupied;
    }

    public void parkVehicle() {
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.isOccupied = false;
    }

    public UUID getSlotId(){
        return this.slotId;
    }

    public VehicleType getVehicleType() {
        return this.vehicleType;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    @Override
    public String toString(){
        return "SlotId: " + this.slotId +
                " VehicleType: " + this.vehicleType +
                " IsOccupied: " + this.isOccupied;
    }
}
