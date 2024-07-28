package main.biddingsystem.model;

import java.util.UUID;

public class Member {

    private UUID memberId;

    private String name;

    private int coins;


    public Member(String name, int coins) {
        this.memberId = UUID.randomUUID();
        this.name = name;
        this.coins = coins;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + memberId +
                ", name='" + name + '\'' +
                ", coins= '" + coins + '\'' +
                '}';
    }
}
