package main.vendingmachine.exceptions;

public class NotSufficientChangeException extends RuntimeException{

    private String message;

    public NotSufficientChangeException(String message){
        super(message);
    }


}
