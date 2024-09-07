package main.meetingscheduler;

import main.meetingscheduler.dao.MeetingDao;
import main.meetingscheduler.models.Meeting;
import main.meetingscheduler.models.MeetingRoom;
import main.meetingscheduler.models.Slot;
import main.meetingscheduler.models.User;
import main.meetingscheduler.service.MeetingScheduler;
import main.meetingscheduler.service.NotificationService;
import main.meetingscheduler.service.RoomManagementService;
import main.meetingscheduler.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class MeetingSchedulerApplication {

    public static void main(String[] args) {

        MeetingDao meetingDao = new MeetingDao();

        NotificationService notificationService = new NotificationService();
        RoomManagementService meetingService = new RoomManagementService(meetingDao);
        UserService userService = new UserService();

        MeetingScheduler meetingScheduler = new MeetingScheduler(meetingDao, notificationService, userService);

        // Define meeting rooms
        MeetingRoom meetingRoom1 = new MeetingRoom(20, "Gryffindor");
        MeetingRoom meetingRoom2 = new MeetingRoom(15, "Ravenclaw");
        MeetingRoom meetingRoom3 = new MeetingRoom(14, "Slytherin");
        MeetingRoom meetingRoom4 = new MeetingRoom(13, "Hufflepuff");
        MeetingRoom meetingRoom5 = new MeetingRoom(12, "Dumbledorks");
        MeetingRoom meetingRoom6 = new MeetingRoom(10, "Marauders");
        MeetingRoom meetingRoom7 = new MeetingRoom(6, "Accio beers");
        MeetingRoom meetingRoom8 = new MeetingRoom(5, "Beaters");
        MeetingRoom meetingRoom9 = new MeetingRoom(5, "Granger Danger");

        // Add meeting rooms to the service
        meetingService.addMeetingRoom(meetingRoom1);
        meetingService.addMeetingRoom(meetingRoom2);
        meetingService.addMeetingRoom(meetingRoom3);
        meetingService.addMeetingRoom(meetingRoom4);
        meetingService.addMeetingRoom(meetingRoom5);
        meetingService.addMeetingRoom(meetingRoom6);
        meetingService.addMeetingRoom(meetingRoom7);
        meetingService.addMeetingRoom(meetingRoom8);
        meetingService.addMeetingRoom(meetingRoom9);

        // Create a user
        User user1 = new User("Harry", "Potter", "harrypotter@hogwarts.com");
        User user2 = new User("Hermione", "Granger", "hermionegranger@hogwarts.com");
        User user3 = new User("Ron", "Weasely", "ronweasely@hogwarts.com");
        User user4 = new User("Draco", "Malfoy", "dracomalfoy@hogwarts.com");
        User user5 = new User("Luna", "Lovegood", "lunalovegood@hogwarts.com");

        // Define meeting times
        LocalDate today = LocalDate.now();
        LocalDateTime startTime1 = today.atTime(LocalTime.now().plusHours(1L));
        LocalDateTime endTime1 = today.atTime(LocalTime.now().plusHours(2));

        LocalDate tomorrow = today.plusDays(1);
        LocalDateTime startTime2 = tomorrow.atTime(LocalTime.of(10, 0));
        LocalDateTime endTime2 = tomorrow.atTime(LocalTime.of(12, 0));

        // Create meetings
        Meeting meeting1 = new Meeting("Daily Standup", today, new Slot(startTime1, endTime1), user1);
        Meeting meeting2 = new Meeting("Quick catchup", tomorrow, new Slot(startTime2, endTime2), user1);

        meeting1.addAttendees(user1);
        meeting1.addAttendees(user2);
        meeting1.addAttendees(user3);
        meeting1.addAttendees(user4);

        meeting2.addAttendees(user1);
        meeting2.addAttendees(user4);
        meeting2.addAttendees(user5);

        // Schedule meetings
        meetingScheduler.scheduleMeeting("Gryffindor", meeting1);
        System.out.println(user1.getUserCalendarOnDate(today));
        System.out.println(user2.getUserCalendarOnDate(today));
        System.out.println(user3.getUserCalendarOnDate(today));
        System.out.println(user4.getUserCalendarOnDate(today));

        meetingScheduler.scheduleMeeting("Gryffindor", meeting2);
        System.out.println(user1.getUserCalendarOnDate(tomorrow));
        System.out.println(user2.getUserCalendarOnDate(tomorrow));
        System.out.println(user3.getUserCalendarOnDate(tomorrow));
        System.out.println(user4.getUserCalendarOnDate(tomorrow));

        // Check available rooms
        Set<String> availableRooms = meetingScheduler.getAllAvailableRooms(tomorrow, new Slot(startTime2, endTime2));
        System.out.println("Available Rooms: " + availableRooms);

        meetingScheduler.cancelMeeting("Gryffindor", meeting1);
        System.out.println(user1.getUserCalendarOnDate(today));
        System.out.println(user2.getUserCalendarOnDate(today));
        System.out.println(user3.getUserCalendarOnDate(today));
        System.out.println(user4.getUserCalendarOnDate(today));

        var todayAvailableRooms = meetingScheduler.getAllAvailableRooms(today, new Slot(startTime1, endTime1));
        System.out.println("Available rooms: " + todayAvailableRooms);
    }
}
