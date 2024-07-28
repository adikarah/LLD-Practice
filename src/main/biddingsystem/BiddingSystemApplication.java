package main.biddingsystem;

import main.biddingsystem.model.Bid;
import main.biddingsystem.model.Event;
import main.biddingsystem.model.Member;
import main.biddingsystem.repository.EventRepository;
import main.biddingsystem.repository.MemberRepository;
import main.biddingsystem.services.EventService;
import main.biddingsystem.services.MemberService;

import java.time.LocalDate;

public class BiddingSystemApplication {

    public static void main(String[] args) {
        MemberRepository userRepository = new MemberRepository();
        EventRepository eventsRepository = new EventRepository(userRepository);
        EventService eventsService = new EventService(eventsRepository, userRepository);
        MemberService userService = new MemberService(userRepository);

        Member u1 = userService.createMember("Vaibhav", 1000);
        Member u2 = userService.createMember("Shyam", 1000);
        Member u3 = userService.createMember("Ram", 5000);
        Event e = eventsService.createEvent("Bidding Carnival", LocalDate.of(2024, 7, 6), "Iphone");

        eventsService.addParticipant(u1, e.getEventId());
        eventsService.addParticipant(u2, e.getEventId());
        eventsService.addParticipant(u3, e.getEventId());

        eventsService.submitBidding(u1.getMemberId(), e.getEventId(), 400);
        eventsService.submitBidding(u2.getMemberId(), e.getEventId(), 100);

        eventsService.submitBidding(u3.getMemberId(), e.getEventId(), 100);
        eventsService.submitBidding(u3.getMemberId(), e.getEventId(), 200);
        eventsService.submitBidding(u3.getMemberId(), e.getEventId(), 300);
        eventsService.submitBidding(u3.getMemberId(), e.getEventId(), 400);
        eventsService.submitBidding(u3.getMemberId(), e.getEventId(), 600);

        Bid winner = eventsService.declareWinner(e.getEventId());
        userService.showBalance();
        System.out.println("Winner is :" + winner.getMember().getName() + " with bid of :" + winner.getAmount());

        eventsService.top5Events();
    }
}
