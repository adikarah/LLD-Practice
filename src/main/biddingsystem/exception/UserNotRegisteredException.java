package main.biddingsystem.exception;

public class UserNotRegisteredException extends RuntimeException {

    public UserNotRegisteredException(String message){
        super(message);
    }
}
