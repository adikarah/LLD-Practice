package main.parkinglot.models;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {

    private final int floorNumber;
    private final List<ParkingSlot> parkingSlots;


    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSlots = new ArrayList<>();
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }

    public void addParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlots.add(parkingSlot);
    }

    public List<ParkingSlot> getParkingSlots(){
        return this.parkingSlots;
    }
}
