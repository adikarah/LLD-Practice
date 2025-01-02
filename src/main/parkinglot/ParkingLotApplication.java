package main.parkinglot;

import main.parkinglot.dao.ParkingLotDao;
import main.parkinglot.dao.VehicleDao;
import main.parkinglot.models.ParkingFloor;
import main.parkinglot.models.ParkingSlot;
import main.parkinglot.models.enums.Vehicle;
import main.parkinglot.models.enums.VehicleType;
import main.parkinglot.services.FloorManager;
import main.parkinglot.services.ParkingSlotManager;
import main.parkinglot.services.PaymentService;

public class ParkingLotApplication {

    public static void main(String[] args) throws Exception {

        ParkingFloor parkingFloor1 = new ParkingFloor(1);
        parkingFloor1.addParkingSlot(new ParkingSlot(VehicleType.TRUCK, false));
        parkingFloor1.addParkingSlot(new ParkingSlot(VehicleType.TRUCK, false));
        parkingFloor1.addParkingSlot(new ParkingSlot(VehicleType.TRUCK, false));

        ParkingFloor parkingFloor2 = new ParkingFloor(2);
        parkingFloor2.addParkingSlot(new ParkingSlot(VehicleType.CAR, false));
        parkingFloor2.addParkingSlot(new ParkingSlot(VehicleType.CAR, false));
        parkingFloor2.addParkingSlot(new ParkingSlot(VehicleType.CAR, false));
        parkingFloor2.addParkingSlot(new ParkingSlot(VehicleType.CAR, false));
        parkingFloor2.addParkingSlot(new ParkingSlot(VehicleType.CAR, false));
        parkingFloor2.addParkingSlot(new ParkingSlot(VehicleType.CAR, false));

        ParkingFloor parkingFloor3 = new ParkingFloor(3);
        parkingFloor3.addParkingSlot(new ParkingSlot(VehicleType.BIKE, false));
        parkingFloor3.addParkingSlot(new ParkingSlot(VehicleType.BIKE, false));
        parkingFloor3.addParkingSlot(new ParkingSlot(VehicleType.BIKE, false));
        parkingFloor3.addParkingSlot(new ParkingSlot(VehicleType.BIKE, false));
        parkingFloor3.addParkingSlot(new ParkingSlot(VehicleType.BIKE, false));

        Vehicle vehicle1 = new Vehicle("23 BH 1234", VehicleType.CAR);
        Vehicle vehicle2 = new Vehicle("24 BH 1234", VehicleType.CAR);
        Vehicle vehicle3 = new Vehicle("25 BH 1234", VehicleType.CAR);
        Vehicle vehicle4 = new Vehicle("18 BH 1234", VehicleType.BIKE);
        Vehicle vehicle5 = new Vehicle("19 BH 1234", VehicleType.BIKE);
        Vehicle vehicle6 = new Vehicle("27 BH 1234", VehicleType.TRUCK);
        Vehicle vehicle7 = new Vehicle("28 BH 1234", VehicleType.TRUCK);

        ParkingLotDao parkingLotDao = new ParkingLotDao();
        VehicleDao vehicleDao = new VehicleDao();

        PaymentService paymentService = new PaymentService();
        FloorManager floorManager = new FloorManager(parkingLotDao);
        ParkingSlotManager parkingSlotManager = new ParkingSlotManager(parkingLotDao, vehicleDao, paymentService);

        floorManager.addNewFloor(parkingFloor1);
        floorManager.addNewFloor(parkingFloor2);
        floorManager.addNewFloor(parkingFloor3);

//        System.out.println("Slots are: " + parkingLotDao.getAllParkingSlot(1));
//        System.out.println("Slots are: " + parkingLotDao.getAllParkingSlot(2));
//        System.out.println("Slots are: " + parkingLotDao.getAllParkingSlot(3));

        parkingSlotManager.parkNewVehicle(1, vehicle7);
        parkingSlotManager.parkNewVehicle(2, vehicle1);
//        System.out.println("Slots are: " + parkingLotDao.getAllParkingSlot(2));
//        System.out.println("Slots are: " + parkingLotDao.getAllParkingSlot(1));
        parkingSlotManager.parkNewVehicle(2, vehicle3);
        parkingSlotManager.parkNewVehicle(2, vehicle2);
        parkingSlotManager.parkNewVehicle(3, vehicle4);

        parkingSlotManager.exitVehicle(vehicle1);

        parkingSlotManager.parkNewVehicle(1, vehicle6);
        parkingSlotManager.parkNewVehicle(1, vehicle7);

        parkingSlotManager.exitVehicle(vehicle6);
        parkingSlotManager.exitVehicle(vehicle3);

        parkingSlotManager.parkNewVehicle(3, vehicle5);
        parkingSlotManager.parkNewVehicle(3, vehicle4);
        parkingSlotManager.parkNewVehicle(3, vehicle2);

    }
}
