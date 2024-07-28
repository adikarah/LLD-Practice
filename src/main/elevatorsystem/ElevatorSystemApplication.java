package main.elevatorsystem;

import main.elevatorsystem.models.Direction;
import main.elevatorsystem.models.Elevator;
import main.elevatorsystem.models.Floor;
import main.elevatorsystem.models.Request;
import main.elevatorsystem.services.ElevatorController;

import java.util.Arrays;
import java.util.List;

public class ElevatorSystemApplication {

    public static void main(String[] args) {

        Elevator elevator1 = new Elevator(1);
        Elevator elevator2 = new Elevator(2);
        Elevator elevator3 = new Elevator(3);

        List<Elevator> elevators = Arrays.asList(elevator1, elevator2, elevator3);

        Floor floor0 = new Floor(0);
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        Floor floor3 = new Floor(3);
        Floor floor4 = new Floor(4);
        Floor floor5 = new Floor(5);
        Floor floor6 = new Floor(6);
        Floor floor7 = new Floor(7);
        Floor floor8 = new Floor(8);
        Floor floor9 = new Floor(9);

        List<Floor> floors = Arrays.asList(floor0, floor1, floor2, floor3, floor4, floor5, floor6, floor7, floor8, floor9);

        ElevatorController elevatorController = new ElevatorController(elevators, floors);

        Request request1 = new Request(0, 5, Direction.UPWARDS);
        Request request2 = new Request(4, 0, Direction.DOWNWARDS);
        Request request3 = new Request(7, 9, Direction.UPWARDS);
        Request request4 = new Request(3, 6, Direction.UPWARDS);
        Request request5 = new Request(3, 8, Direction.UPWARDS);
        Request request6 = new Request(9, 6, Direction.DOWNWARDS);

        elevatorController.requestElevator(request1);
        elevatorController.requestElevator(request2);
        elevatorController.requestElevator(request3);
        elevatorController.requestElevator(request4);
        elevatorController.requestElevator(request5);
        elevatorController.requestElevator(request6);

        for (int i = 0; i < 10; i++){
            elevatorController.stepElevator();
        }


    }
}
