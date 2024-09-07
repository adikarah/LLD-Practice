package main.biddingsystem.repository;

import main.biddingsystem.exception.InvalidInputException;
import main.biddingsystem.exception.SlotNotFoundException;
import main.biddingsystem.exception.UserNotRegisteredException;
import main.biddingsystem.model.Bid;
import main.biddingsystem.model.Event;
import main.biddingsystem.model.Member;

import java.time.LocalDate;
import java.util.*;

public class EventRepository {

    private static final int BID_LIMIT = 5;
    private MemberRepository memberRepository;
    private HashMap<UUID, Event> eventDatabase;
    private HashMap<LocalDate, Boolean> calendar;
    private HashMap<UUID, Queue<Bid>> biddingSubmitted;
    private HashMap<UUID, HashMap<UUID, HashSet<Bid>>> biddingDatabase;

    public EventRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.eventDatabase = new HashMap<>();
        this.calendar = new HashMap<>();
        this.biddingDatabase = new HashMap<>();
        this.biddingSubmitted = new HashMap<>();
    }

    public Event addEvent(String eventName, String prize, LocalDate eventDate) {
        Event event = new Event(eventName, prize, eventDate);

        if (slotFound(eventDate)) {
            calendar.put(eventDate, true);
            eventDatabase.put(event.getEventId(), event);
            biddingDatabase.put(event.getEventId(), new HashMap<>());
            biddingSubmitted.put(event.getEventId(), new ArrayDeque<>());
            return event;
        } else {
            throw new SlotNotFoundException("No slot found for given date: " + eventDate);
        }
    }

    public void addParticipant(Member member, UUID eventId) {
        if (eventDatabase.containsKey(eventId)) {
            Event e = eventDatabase.get(eventId);
            e.getEventParticipants().add(member.getMemberId());
            biddingDatabase.get(eventId).put(member.getMemberId(), new HashSet<>());
        }
    }

    public void submitBidding(Bid bid, UUID eventId) {

        /// validate registered or not
        if (validateUserForBidding(bid, eventId)) {
            Member member = bid.getMember();
            checkEventActiveForBidding(eventId);
            updateUserCoins(bid, eventId);
            biddingDatabase.get(eventId).get(member.getMemberId()).add(bid);
            biddingSubmitted.get(eventId).add(bid);

        }
    }


    public Bid declareWinner(UUID eventId) {
        Event e = eventDatabase.get(eventId);
        Queue<Bid> bids = biddingSubmitted.get(eventId);
        Bid winner = null;
        while (bids.size() > 0) {
            Bid b = bids.remove();
            if (winner == null) winner = b;
            else winner = b.getAmount() < winner.getAmount() ? b : winner;
        }
        e.setWinningBid(winner);
        e.setActive(false);
        biddingDatabase.remove(eventId);
        biddingSubmitted.remove(eventId);
        System.out.println("Winner is being loaded, contest" + e.getEventName()
                + " ended with id : " + e.getEventId() + " ");
        return winner;
    }


    private void updateUserCoins(Bid b, UUID e) {
        Member member = b.getMember();
        int maxBidAlreadyDone = 0;
        if (biddingDatabase.containsKey(e) && biddingDatabase.get(e).containsKey(member.getMemberId())) {
            HashSet<Bid> doneBiddings = biddingDatabase.get(e).get(member.getMemberId());
            for (Bid ele : doneBiddings) maxBidAlreadyDone = Math.max(ele.getAmount(), maxBidAlreadyDone);
        }
        member.setCoins(member.getCoins() - b.getAmount() + maxBidAlreadyDone);
    }

    private boolean validateUserForBidding(Bid b, UUID eventId) {
        Member u = b.getMember();
        Event e = eventDatabase.get(eventId);
        if (!e.getEventParticipants().contains(b.getMember().getMemberId())) {
            // user not participated
            throw new UserNotRegisteredException("User is not registered in event");
        }
        // check bidings
        HashMap<UUID, HashSet<Bid>> userBids = biddingDatabase.get(eventId);
        if (userBids != null && userBids.get(b.getMember().getMemberId()).size() >= BID_LIMIT) {
            throw new InvalidInputException("No of bids exceeded ");
        }
        if (userBids != null && userBids.get(u.getMemberId()).contains(b)) {
            throw new InvalidInputException("Duplicate bids  not allowed");
        }
        if (u.getCoins() < b.getAmount()) {
            // user have less coins
            throw new InvalidInputException("User don't have enough coins ");
        }

        return true;

    }

    private void checkEventActiveForBidding(UUID eventId) {
        LocalDate now = LocalDate.now();
        Event e = eventDatabase.get(eventId);
        if (e.getEventDate().isBefore(now) || !e.isActive()) {
            throw new RuntimeException("Event is expired or not active anymore");
        }
    }

    public void showTop5Events() {
        eventDatabase.values().stream().
                sorted().
                limit(5L)
                .forEach(event -> {
                    System.out.println("Event of date : " + event.getEventDate()
                            + " won by: " + event.getWinningBid().getMember().getName()
                            + " with a bid of : " + event.getWinningBid().getAmount());
                });

    }


    private boolean slotFound(LocalDate eventDate) {
        return !calendar.containsKey(eventDate);
    }
}
