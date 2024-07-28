package main.biddingsystem.model;

public class Bid {

    private Member member;

    private int amount;

    public Bid(Member member, int amount) {
        this.member = member;
        this.amount = amount;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
