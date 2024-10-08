package main.vendingmachine.enums;

public enum Coin {
    PENNY(1),
    NICKLE(5),
    DIME(10),
    QUARTER(25);

    private final int val;

    Coin(int val) {
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }
}
