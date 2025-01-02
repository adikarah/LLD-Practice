package main.parkinglot.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class VehicleParkingDetails {

    UUID slotNumber;
    LocalDateTime entryTime;
    LocalDateTime exitTime;

    public VehicleParkingDetails(UUID slotNumber, LocalDateTime entryTime) {
        this.slotNumber = slotNumber;
        this.entryTime = entryTime;
    }

    public UUID getSlotNumber() {
        return this.slotNumber;
    }

    public LocalDateTime getEntryTime() {
        return this.entryTime;
    }

    public void setExitTime(LocalDateTime time) {
        this.exitTime = time;
    }

    @Override
    public String toString() {
        return "SlotNumber: " + this.slotNumber
                + " Entry Time: " + this.entryTime
                + " Exit Time: " + this.exitTime;
    }
}
