package main.bookmyshow.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MovieTiming(

        LocalDate movieDate,
        LocalDateTime startTime,
        LocalDateTime endTime) {

}

