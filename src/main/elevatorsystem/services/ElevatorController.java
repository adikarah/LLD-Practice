package main.elevatorsystem.services;

import main.elevatorsystem.models.*;

import java.util.Comparator;
import java.util.List;

public class ElevatorController {

    private List<Elevator> elevators;
    private List<Floor> floors;

    public ElevatorController(List<Elevator> elevators, List<Floor> floors) {
        this.elevators = elevators;
        this.floors = floors;
    }

    public void requestElevator(Request request) {
        Floor sourceFloor = floors.get(request.getCurrentFloor());
        sourceFloor.addRequest(request);

        Elevator bestElevator = null;

        // Step 1: Check for any idle elevators
        for (Elevator elevator : elevators) {
            if (elevator.getStatus() == Status.IDLE) {
                bestElevator = elevator;
                System.out.println("Your request will be fulfilled by elevator " + bestElevator.getElevatorId());
                break;
            }
        }

        if (bestElevator != null) {
            bestElevator.addRequest(request);
        } else {
            // Step 2: Sort elevators based on distance to the request source floor
            List<Elevator> sortedElevators = elevators.stream()
                    .sorted(Comparator.comparingInt(elevator -> Math.abs(elevator.getCurrentFloor() - request.getCurrentFloor()))).toList();

            // Step 3: Filter sorted elevators to find ones matching the request direction
            List<Elevator> matchedElevators = sortedElevators.stream()
                    .filter(elevator -> elevator.getDirection() == request.getDirection() ||
                            elevator.getDirection() == Direction.IDLE).toList();

            if (!matchedElevators.isEmpty()) {
                bestElevator = matchedElevators.get(0);
            } else {
                bestElevator = sortedElevators.get(0); // Select the closest elevator if no matching direction found
            }

            bestElevator.addRequest(request);
            System.out.println("Your request will be fulfilled by elevator " + bestElevator.getElevatorId());
        }
    }

    public void stepElevator() {
        for (Elevator elevator : elevators) {
            elevator.move();
        }
    }

}
