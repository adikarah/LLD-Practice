package main.biddingsystem.repository;

import main.biddingsystem.exception.MemberNotFoundException;
import main.biddingsystem.model.Member;

import java.util.HashMap;
import java.util.UUID;

public class MemberRepository {

    private HashMap<UUID, Member> userDatabase;

    public MemberRepository() {
        this.userDatabase = new HashMap<>();
    }

    public Member createUser(String name, int coins) {
        Member member = new Member(name, coins);
        userDatabase.put(member.getMemberId(), member);
        return member;
    }

    public Member getMemberById(UUID id) {
        if (userDatabase.get(id) != null) {
            return userDatabase.get(id);
        } else {
            throw new MemberNotFoundException("No member found with provided id:" + id);
        }
    }

    public void showBalance() {
        for(UUID key: userDatabase.keySet()){
            Member user = userDatabase.get(key);
            System.out.println("Current Balance for : "+user.getName()+" is: "+user.getCoins());
        }
    }
}
