package main.bookmyshow.models;

import java.time.LocalDateTime;

public record MovieDuration(
        LocalDateTime startTime,
        LocalDateTime endTime
) {

}
