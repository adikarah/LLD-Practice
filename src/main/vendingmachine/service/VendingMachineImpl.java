package main.vendingmachine.service;

import main.vendingmachine.enums.Coin;
import main.vendingmachine.enums.Item;
import main.vendingmachine.exceptions.NotFullPaidException;
import main.vendingmachine.exceptions.NotSufficientChangeException;
import main.vendingmachine.exceptions.SoldOutException;
import main.vendingmachine.utility.Bucket;
import main.vendingmachine.utility.Inventory;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {

    public Inventory<Coin> cashInventory = new Inventory<>();
    public Inventory<Item> itemInventory = new Inventory<>();
    private long totalSales;
    private Item currentItem;
    private long currentBalance;

    public VendingMachineImpl() {
        initialize();
    }

    private void initialize() {
        for (Coin coin : Coin.values()) {
            cashInventory.addItem(coin);
        }

        for (Item item : Item.values()) {
            itemInventory.addItem(item);
        }
    }


    @Override
    public long selectItemAndGetPrice(Item item) {
        if (itemInventory.getQuantity(item) != 0) {
            return item.getPrice();
        } else {
            throw new SoldOutException("This item has been sold out");
        }
    }

    @Override
    public void insertCoin(Coin coin) {
        currentBalance += coin.getVal();
        cashInventory.addItem(coin);
    }

    @Override
    public List<Coin> refund() {
        List<Coin> refund = getChange(currentBalance);
        updateCashInventory(refund);
        currentBalance = 0;
        currentItem = null;
        return refund;

    }

    @Override
    public Bucket<Item, List<Coin>> collectItemAndChange() {
        Item item = collectItem();
        totalSales += currentItem.getPrice();
        List<Coin> changes = getChange(currentBalance - currentItem.getPrice());

        return new Bucket<>(item, changes);
    }

    @Override
    public void reset() {
        cashInventory.clear();
        itemInventory.clear();
        totalSales = 0;
        currentBalance = 0;
        currentItem = null;
    }

    private Item collectItem() throws NotSufficientChangeException, NotFullPaidException {
        if (isFullPaid()) {
            if (hasSufficientChange()) {
                itemInventory.deductItem(currentItem);
                return currentItem;
            }
            throw new NotSufficientChangeException("Not sufficient change in inventory");
        }

        long remainingBalance = currentItem.getPrice() - currentBalance;
        throw new NotFullPaidException("Not sufficient balance in your wallet. Please add ", remainingBalance);

    }

    private boolean isFullPaid() {
        return currentBalance >= currentItem.getPrice();
    }

    private List<Coin> getChange(long amount) throws NotSufficientChangeException {
        List<Coin> changes = new ArrayList<>();

        if (amount > 0) {
            changes = new ArrayList<>();
            long balance = amount;
            while (balance > 0) {
                if (balance >= Coin.QUARTER.getVal()
                        && cashInventory.hasItem(Coin.QUARTER)) {
                    changes.add(Coin.QUARTER);
                    balance = balance - Coin.QUARTER.getVal();

                } else if (balance >= Coin.DIME.getVal()
                        && cashInventory.hasItem(Coin.DIME)) {
                    changes.add(Coin.DIME);
                    balance = balance - Coin.DIME.getVal();

                } else if (balance >= Coin.NICKLE.getVal()
                        && cashInventory.hasItem(Coin.NICKLE)) {
                    changes.add(Coin.NICKLE);
                    balance = balance - Coin.NICKLE.getVal();

                } else if (balance >= Coin.PENNY.getVal()
                        && cashInventory.hasItem(Coin.PENNY)) {
                    changes.add(Coin.PENNY);
                    balance = balance - Coin.PENNY.getVal();

                } else {
                    throw new NotSufficientChangeException("NotSufficientChange, Please try another product ");
                }
            }
        }

        return changes;
    }

    private boolean hasSufficientChange() {
        return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
    }

    private boolean hasSufficientChangeForAmount(long amount) {
        boolean hasChange = true;
        try {
            getChange(amount);
        } catch (NotSufficientChangeException e) {
            hasChange = false;
        }

        return hasChange;
    }

    private void updateCashInventory(List<Coin> change) {
        for (Coin coin : change) {
            cashInventory.deductItem(coin);
        }
    }

    public long getTotalSales() {
        return totalSales;
    }

    public void printStats() {
        System.out.println("Total sales: " + totalSales);
        System.out.println("Current item inventory: " + itemInventory);
        System.out.println("Current cash inventory: " + cashInventory);
    }


}
