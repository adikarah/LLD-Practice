package main.bookmyshow.models;

import java.util.UUID;

public class Seat {
    private UUID seatId;
    private SeatType seatType;
    private Double price;

    public Seat(SeatType seatType, Double price) {
        this.seatId = UUID.randomUUID();
        this.seatType = seatType;
        this.price = price;
    }

}
