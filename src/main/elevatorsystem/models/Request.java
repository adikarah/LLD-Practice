package main.elevatorsystem.models;

public class Request {
    private int currentFloor;
    private int destinationFloor;
    private Direction direction;

    public Request(int currentFloor, int destinationFloor, Direction direction) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}
