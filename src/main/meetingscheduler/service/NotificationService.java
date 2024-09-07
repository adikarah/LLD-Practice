package main.meetingscheduler.service;

import main.meetingscheduler.models.User;

public class NotificationService {

    public void sendNotification(User user, String message) {
        System.out.println("Sending notification to: " + user.getEmailId() + " " + message);
    }
}
