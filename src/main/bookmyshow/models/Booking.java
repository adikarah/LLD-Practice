package main.bookmyshow.models;

import java.util.UUID;

public class Booking {

    private UUID bookingId;
    private Movie movie;
    private BookingStatus bookingStatus;

    private String theaterName;

    public Booking(UUID bookingId, Movie movie, String theaterName) {
        this.bookingId = bookingId;
        this.movie = movie;
        this.bookingStatus = BookingStatus.IN_PROCESS;
        this.theaterName = theaterName;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }
}
