package main.meetingscheduler.exception;

public class RoomAlreadyAddedException extends RuntimeException {

    public RoomAlreadyAddedException(String message) {
        super(message);
    }
}
