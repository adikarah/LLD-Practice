package main.biddingsystem.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Event {

    private UUID eventId;

    private String eventName;

    private String eventPrize;

    private LocalDate eventDate;

    private Set<UUID> eventParticipants;

    private Bid winningBid;

    private boolean isActive;

    public Event(String eventName, String eventPrize, LocalDate eventDate) {
        this.eventId = UUID.randomUUID();
        this.eventName = eventName;
        this.eventPrize = eventPrize;
        this.eventDate = eventDate;
        this.eventParticipants = new HashSet<>();
        this.isActive = true;
    }

    public UUID getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventPrize() {
        return eventPrize;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public Set<UUID> getEventParticipants() {
        return eventParticipants;
    }

    public Bid getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(Bid bid){
        this.winningBid = bid;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean status){
        this.isActive = status;
    }
}
