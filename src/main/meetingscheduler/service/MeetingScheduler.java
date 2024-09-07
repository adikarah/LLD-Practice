package main.meetingscheduler.service;

import main.meetingscheduler.dao.MeetingDao;
import main.meetingscheduler.models.Meeting;
import main.meetingscheduler.models.MeetingRoom;
import main.meetingscheduler.models.Slot;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MeetingScheduler {

    private final MeetingDao meetingDao;

    private final NotificationService notificationService;

    private final UserService userService;


    public MeetingScheduler(MeetingDao meetingDao, NotificationService notificationService, UserService userService) {
        this.meetingDao = meetingDao;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    public void scheduleMeeting(String roomName, Meeting meeting) {
        meetingDao.addMeeting(roomName, meeting);

        // Notify attendees asynchronously
        String notificationMessage = String.format(
                "You have been invited to join a new meeting '%s' on '%s' at '%s' in room '%s'",
                meeting.getMeetingTitle(),
                meeting.getMeetingDate().toString(),
                meeting.getMeetingDuration().toString(),
                roomName
        );


        List<CompletableFuture<Void>> futures = meeting.getAttendees().stream().map(attendee ->
                CompletableFuture.runAsync(() -> {
                    userService.addMeetingToCalendar(attendee, meeting);

                    // Send notification if the meeting is added successfully
                    if (!attendee.getEmailId().equals(meeting.getHost().getEmailId())) {
                        notificationService.sendNotification(attendee, notificationMessage);
                    }
                })).toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println("Meeting scheduled successfully !!!");
    }

    public void cancelMeeting(String roomName, Meeting meeting) {
        meetingDao.removeMeeting(roomName, meeting);

        // Notify attendees asynchronously
        String notificationMessage = String.format(
                "%s cancelled your scheduled meeting '%s' on %s at %s in room %s.",
                meeting.getHost().getFName(),
                meeting.getMeetingTitle(),
                meeting.getMeetingDate().toString(),
                meeting.getMeetingDuration().toString(),
                roomName
        );

        List<CompletableFuture<Void>> futures = meeting.getAttendees().stream().map(attendee ->
                CompletableFuture.runAsync(() -> {
                            userService.removeMeetingFromCalendar(attendee, meeting);
                            if (!meeting.getHost().getEmailId().equals(attendee.getEmailId())) {
                                notificationService.sendNotification(attendee, notificationMessage);
                            }
                        }
                )
        ).toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println("Meeting cancelled successfully !!!");
    }

    public Set<String> getAllAvailableRooms(LocalDate meetingDate, Slot meetingTimings) {
        var allRooms = meetingDao.getAllMeetingRooms().stream().map(
                MeetingRoom::getRoomName
        ).collect(Collectors.toSet());

        allRooms.removeIf(meetingRoom ->
                !meetingDao.isMeetingRoomAvailable(meetingRoom, meetingDate, meetingTimings)
        );

        return allRooms;
    }
}
