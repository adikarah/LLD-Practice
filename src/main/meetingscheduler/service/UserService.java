package main.meetingscheduler.service;

import main.meetingscheduler.models.Meeting;
import main.meetingscheduler.models.Slot;
import main.meetingscheduler.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserService {

    public void addMeetingToCalendar(User user, Meeting meeting) {
        HashMap<Slot, List<Meeting>> meetingsForDay = user.getMeetingCalendar()
                .computeIfAbsent(meeting.getMeetingDate(), date -> new HashMap<>());

        List<Meeting> meetingsInSlot = meetingsForDay.get(meeting.getMeetingDuration());

        if (meetingsInSlot != null && !meetingsInSlot.isEmpty()) {
            // Conflict detected: there's already a meeting scheduled in this slot
            var conflictMessage =
                    String.format("Conflict: A meeting is already scheduled on %s during %s.",
                            meeting.getMeetingDate().toString(),
                            meeting.getMeetingDuration().toString());
            System.out.println(conflictMessage);

        }

        // Proceed to schedule the meeting anyway
        meetingsForDay.computeIfAbsent(meeting.getMeetingDuration(), slot -> new ArrayList<>())
                .add(meeting);
    }


    public void removeMeetingFromCalendar(User user, Meeting meeting) {
        user.getMeetingCalendar().get(meeting.getMeetingDate())
                .get(meeting.getMeetingDuration())
                .remove(meeting);
    }
}
