package main.vendingmachine.service;

import main.vendingmachine.enums.Coin;
import main.vendingmachine.enums.Item;
import main.vendingmachine.utility.Bucket;

import java.util.List;

/**
 * Declare public API for Vending Machine
 *
 * @author Praveen Tripathi
 */


public interface VendingMachine {

    long selectItemAndGetPrice(Item item);

    void insertCoin(Coin coin);

    List<Coin> refund();

    Bucket<Item, List<Coin>> collectItemAndChange();

    void reset();

}
