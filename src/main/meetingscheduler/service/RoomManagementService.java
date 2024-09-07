package main.meetingscheduler.service;

import main.meetingscheduler.dao.MeetingDao;
import main.meetingscheduler.models.MeetingRoom;
import main.meetingscheduler.models.Slot;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class RoomManagementService {


    private final MeetingDao meetingDao;

    public RoomManagementService(MeetingDao meetingDao) {
        this.meetingDao = meetingDao;
    }

    public void addMeetingRoom(MeetingRoom meetingRoom) {
        meetingDao.addNewRoom(meetingRoom);
        System.out.println("Room " + meetingRoom.getRoomName() + " added successfully!!!");
    }

    public void removeMeetingRoom(String roomName) {
        meetingDao.removeRoom(roomName);
    }

    public boolean isSlotAvailable(String roomName, LocalDate meetingDate, Slot meetingTimings) {
        return meetingDao.isMeetingRoomAvailable(roomName, meetingDate, meetingTimings);
    }

    public List<String> allRoomsWithCapacity(int capacity) {
        var allRooms = meetingDao.getAllMeetingRooms();
        allRooms.removeIf(meetingRoom ->
                meetingRoom.getCapacity() < capacity
        );

        return allRooms.stream().map(MeetingRoom::getRoomName).collect(Collectors.toList());
    }
}
