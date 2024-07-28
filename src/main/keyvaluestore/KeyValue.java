package main.keyvaluestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class KeyValue<K, V> {
    private Map<K, V> data;
    private final Stack<Map<K, V>> transactions;

    public KeyValue() {
        this.data = new HashMap<>();
        this.transactions = new Stack<>();
    }

    public V get(K key) {
        return data.get(key);
    }

    public void set(K key, V value) {
        data.put(key, value);
    }

    public void delete(K key) {
        data.remove(key);
    }

    public void beginTransaction() {
        transactions.push(new HashMap<>(data));
    }

    public void commitTransaction() {
        transactions.clear();
    }

    public void rollBackTransaction() {
        if (!transactions.isEmpty()) {
            data = transactions.pop();
        }
    }

}
