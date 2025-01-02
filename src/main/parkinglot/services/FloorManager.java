package main.parkinglot.services;

import main.parkinglot.dao.ParkingLotDao;
import main.parkinglot.models.ParkingFloor;

public class FloorManager {

    private final ParkingLotDao parkingLotDao;

    public FloorManager(ParkingLotDao parkingLotDao){
        this.parkingLotDao = parkingLotDao;
    }

    public void addNewFloor(ParkingFloor parkingFloor){
        parkingLotDao.addNewFloor(parkingFloor);
    }
}
