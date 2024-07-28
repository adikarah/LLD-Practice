package main.bookmyshow.services;

import main.bookmyshow.models.Movie;
import main.bookmyshow.models.MovieDuration;
import main.bookmyshow.models.MovieTiming;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public class TheaterService {

    private HashMap<String, Movie> theaterToMovieDatabase; // movieName to movieDetails
    private HashMap<String, Set<MovieTiming>> movieToScheduleDatabase; // movie to movieTimings
    private HashMap<LocalDate, Set<MovieDuration>> movieToTimingDatabase; // showDate to showTimings

    public TheaterService() {
        this.theaterToMovieDatabase = new HashMap<>();
        this.movieToScheduleDatabase = new HashMap<>();
        this.movieToTimingDatabase = new HashMap<>();
    }

    public void screenNewMovie(String theaterName, Movie movie) {
        theaterToMovieDatabase.putIfAbsent(movie.getMovieName(), movie);
    }

    public String removeMovieScreening(String movieName) {
        if (theaterToMovieDatabase.containsKey(movieName)) {
            theaterToMovieDatabase.remove(movieName);
            return movieName + " successfully removed";
        } else {
            throw new RuntimeException("This movie: " + movieName + "is not screening in theater");
        }
    }

    public String setMovieScreeningTime(String movieName, MovieTiming movieTiming) {
        if (theaterToMovieDatabase.containsKey(movieName)) {
            if (isSlotAvailable(movieTiming.movieDate(), new MovieDuration(movieTiming.startTime(), movieTiming.endTime()))) {
                Set<MovieDuration> movieDurations = movieToTimingDatabase.get(movieTiming.movieDate());
                movieDurations.add(new MovieDuration(movieTiming.startTime(), movieTiming.endTime()));
                movieToTimingDatabase.put(movieTiming.movieDate(), movieDurations);
                Set<MovieTiming> movieTimings = movieToScheduleDatabase.get(movieName);
                movieTimings.add(movieTiming);
                movieToScheduleDatabase.put(movieName, movieTimings);
                return "Screening set successfully for movie:" + movieName + "timing:" + movieTiming;
            } else {
                throw new RuntimeException("Slots not available for requested period of time");
            }
        } else {
            throw new RuntimeException("Please add this movie to theater first");
        }
    }


    private boolean isSlotAvailable(LocalDate localDate, MovieDuration movieDuration) {
        if (!movieToTimingDatabase.containsKey(localDate)) {
            return true;
        } else {
            Set<MovieDuration> movieTimings = movieToTimingDatabase.get(localDate);
            return !movieTimings.contains(movieDuration);
        }
    }
}
