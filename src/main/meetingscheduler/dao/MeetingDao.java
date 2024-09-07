package main.meetingscheduler.dao;

import main.meetingscheduler.exception.RoomAlreadyAddedException;
import main.meetingscheduler.exception.RoomAlreadyBookedException;
import main.meetingscheduler.models.Meeting;
import main.meetingscheduler.models.MeetingRoom;
import main.meetingscheduler.models.Slot;

import java.time.LocalDate;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class MeetingDao {

    private static final ConcurrentHashMap<String, ConcurrentHashMap<LocalDate, ConcurrentHashMap<Slot, Meeting>>> meetingsDatabase = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, MeetingRoom> meetingRoomDatabase = new ConcurrentHashMap<>();

    public void addNewRoom(MeetingRoom meetingRoom) {
        if (meetingRoomDatabase.containsKey(meetingRoom.getRoomName())) {
            throw new RoomAlreadyAddedException("There is already a room available with this room");
        } else {
            meetingRoomDatabase.put(meetingRoom.getRoomName(), meetingRoom);
            meetingsDatabase.put(meetingRoom.getRoomName(), new ConcurrentHashMap<>());
        }
    }

    public void removeRoom(String roomName) {
        if (meetingRoomDatabase.containsKey(roomName)) {
            meetingRoomDatabase.remove(roomName);
            meetingsDatabase.remove(roomName);
        } else {
            throw new RuntimeException("No room exists with given name");
        }
    }

    public Collection<MeetingRoom> getAllMeetingRooms() {
        return meetingRoomDatabase.values();
    }


    public void addMeeting(String roomName, Meeting meeting) {
        if (meetingsDatabase.get(roomName).containsKey(meeting.getMeetingDate())) {
            if (meetingsDatabase.get(roomName).get(meeting.getMeetingDate()).containsKey(meeting.getMeetingDuration())) {
                throw new RoomAlreadyBookedException("This room is already booked for given slot !!!");
            } else {
                meetingsDatabase.get(roomName).get(meeting.getMeetingDate()).put(meeting.getMeetingDuration(), meeting);

            }
        } else {
            ConcurrentHashMap<Slot, Meeting> meetingData = new ConcurrentHashMap<>();
            meetingData.put(meeting.getMeetingDuration(), meeting);
            meetingsDatabase.get(roomName).put(meeting.getMeetingDate(), meetingData);
        }
    }

    public void removeMeeting(String roomName, Meeting meeting) {
        meetingsDatabase.get(roomName).get(meeting.getMeetingDate()).remove(meeting.getMeetingDuration());

    }

    public boolean isMeetingRoomAvailable(String roomName, LocalDate meetingDate, Slot meetingTimings) {
        if (meetingsDatabase.get(roomName).containsKey(meetingDate)) {
            return !meetingsDatabase.get(roomName).get(meetingDate).containsKey(meetingTimings);
        }

        return true;

    }
}
