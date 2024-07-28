package main.elevatorsystem.models;

import java.util.ArrayList;
import java.util.List;

public class Elevator {

    private int elevatorId;
    private int currentFloor;
    private Direction direction;
    private Status status;
    private List<Request> requests;

    public Elevator(int elevatorId) {
        this.elevatorId = elevatorId;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.status = Status.IDLE;
        this.requests = new ArrayList<>();
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public Status getStatus() {
        return status;
    }

    public List<Request> getRequest() {
        return requests;
    }

    public void addRequest(Request request){
        requests.add(request);
        if (status == Status.IDLE){
            direction = request.getDestinationFloor() > currentFloor ? Direction.UPWARDS : Direction.DOWNWARDS;
            status = Status.MOVING;
        }
    }

    public void move(){
        if (status == Status.MOVING){
            if (direction == Direction.UPWARDS){
                currentFloor += 1;
            } else if (direction == Direction.DOWNWARDS) {
                currentFloor -= 1;
            }

            if (!requests.isEmpty() && currentFloor == requests.get(0).getDestinationFloor()){
                requests.remove(0);
                if (requests.isEmpty()){
                    status = Status.IDLE;
                    direction = Direction.IDLE;
                }
            }
        }
    }

}
