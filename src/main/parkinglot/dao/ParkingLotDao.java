package main.parkinglot.dao;

import main.parkinglot.models.ParkingFloor;
import main.parkinglot.models.ParkingSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ParkingLotDao {

    HashMap<Integer, List<ParkingSlot>> floorToSlotDatabase;
    HashMap<UUID, ParkingSlot> slotDatabase;

    public ParkingLotDao() {
        this.floorToSlotDatabase = new HashMap<>();
        this.slotDatabase = new HashMap<>();
    }

    public void addNewFloor(ParkingFloor parkingFloor) {
        // add floor details to the database
        int floorNumber = parkingFloor.getFloorNumber();
        if (floorToSlotDatabase.containsKey(floorNumber)) {
            var slots = floorToSlotDatabase.get(floorNumber);
            slots.addAll(parkingFloor.getParkingSlots());
            floorToSlotDatabase.put(floorNumber, slots);
        } else {
            floorToSlotDatabase.put(floorNumber, parkingFloor.getParkingSlots());
        }

        // add slot details to the database
        parkingFloor.getParkingSlots()
                .parallelStream().forEach(
                        parkingSlot -> slotDatabase.put(parkingSlot.getSlotId(), parkingSlot)
                );
    }

    public List<ParkingSlot> getAllParkingSlot(int floorNumber) {
        return floorToSlotDatabase.get(floorNumber);
    }

    public ParkingSlot getParkingSlotDetailsBySlotId(UUID slotId) {
        return this.slotDatabase.get(slotId);
    }


}
