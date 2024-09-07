package main.meetingscheduler.models;

import java.time.LocalDateTime;

public record Slot(LocalDateTime startTime, LocalDateTime endTime) {
}
