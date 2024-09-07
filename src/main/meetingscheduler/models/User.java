package main.meetingscheduler.models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class User {

    private final String fName;

    private final String lName;

    private final String emailId;

    private final HashMap<LocalDate, HashMap<Slot, List<Meeting>>> meetingCalendar;

    public User(String fName, String lName, String emailId) {
        this.fName = fName;
        this.lName = lName;
        this.emailId = emailId;
        this.meetingCalendar = new HashMap<>();
    }

    public String getFName() {
        return this.fName;
    }

    public String getLName() {
        return this.lName;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public HashMap<LocalDate, HashMap<Slot, List<Meeting>>> getMeetingCalendar() {
        return this.meetingCalendar;
    }

    public HashMap<Slot, List<Meeting>> getUserCalendarOnDate(LocalDate date) {
        return this.meetingCalendar.get(date);
    }

}
