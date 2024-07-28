package main.biddingsystem.services;

import main.biddingsystem.model.Member;
import main.biddingsystem.repository.MemberRepository;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member createMember(String name, int coins) {
        if (coins <= 0) throw new RuntimeException("Coin should be greater than 0");
        return memberRepository.createUser(name, coins);
    }

    public void showBalance() {
        memberRepository.showBalance();
    }
}
