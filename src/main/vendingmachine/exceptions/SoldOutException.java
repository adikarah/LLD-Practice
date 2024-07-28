package main.vendingmachine.exceptions;

public class SoldOutException extends RuntimeException {

    private String message;

    public SoldOutException(String message) {
        super(message);
    }
}
