package main.vendingmachine.utility;

import java.util.HashMap;
import java.util.Map;

/**
 * An Adapter over Map to create Inventory to hold cash and
 * Items inside Vending Machine
 *
 * @author Praveen Tripathi
 */


public class Inventory<T> {

    private final Map<T, Integer> inventory = new HashMap<>();

    public int getQuantity(T item) {
        return inventory.getOrDefault(item, 0);
    }

    public void addItem(T item) {
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
    }

    public void deductItem(T item) {
        if (inventory.containsKey(item)) {
            inventory.put(item, inventory.get(item) - 1);
        }
    }

    public void clear() {
        inventory.clear();
    }

    public boolean hasItem(T item){
        return inventory.containsKey(item);
    }

}
