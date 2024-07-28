package main.bookmyshow.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Theatre {

    private UUID theaterId;
    private String theaterName;
    private List<Movie> movieList;
    private Address address;

    public Theatre(String theaterName, Address address){
        this.theaterId = UUID.randomUUID();
        this.theaterName = theaterName;
        this.movieList = new ArrayList<>();
        this.address = address;
    }

    public void addMovie(Movie movie){
        this.movieList.add(movie);
    }

    public UUID getTheaterId(){
        return this.theaterId;
    }

    public String getTheaterName(){
        return this.theaterName;
    }

    public Address address(){
        return this.address;
    }
}
