package main.vendingmachine.exceptions;

public class NotFullPaidException extends RuntimeException {

    private String message;
    private final long remainingBalance;

    public NotFullPaidException(String message, long remainingBalance) {
        super(message);
        this.remainingBalance = remainingBalance;
    }

    public long getRemainingBalance() {
        return this.remainingBalance;
    }

    @Override
    public String getMessage() {
        return this.message + this.remainingBalance;
    }


}
