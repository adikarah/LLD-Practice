package main.biddingsystem.services;

import main.biddingsystem.model.Bid;
import main.biddingsystem.model.Event;
import main.biddingsystem.model.Member;
import main.biddingsystem.repository.EventRepository;
import main.biddingsystem.repository.MemberRepository;

import java.time.LocalDate;
import java.util.UUID;

public class EventService {

    private EventRepository eventRepository;

    private MemberRepository memberRepository;

    public EventService(EventRepository eventRepository, MemberRepository memberRepository) {
        this.eventRepository = eventRepository;
        this.memberRepository = memberRepository;
    }

    public Event createEvent(String eventName, LocalDate eventDate, String prize) {
        return eventRepository.addEvent(eventName, prize, eventDate);
    }

    public void addParticipant(Member member, UUID eventId) {
        eventRepository.addParticipant(member, eventId);
    }

    public void submitBidding(UUID user, UUID eventId, int amt) {

        /// validate registered or not
        Member member = memberRepository.getMemberById(user);
        Bid b = new Bid(member, amt);
        eventRepository.submitBidding(b, eventId);


    }

    public Bid declareWinner(UUID eventId) {
        return eventRepository.declareWinner(eventId);
    }

    public void top5Events() {
        eventRepository.showTop5Events();
    }
}
