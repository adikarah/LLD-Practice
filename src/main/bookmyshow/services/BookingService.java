package main.bookmyshow.services;

import main.bookmyshow.models.BookingStatus;

public class BookingService {

    private TheaterService theaterService;

    public BookingService(TheaterService theaterService){
        this.theaterService = theaterService;
    }

    public BookingStatus bookShow(){
        return BookingStatus.CONFIRMED;
    }
}
