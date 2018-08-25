package Util;

import Util.Item;
import java.util.List;

public class Transaction {
    private int transactionID; // primary key
    private List<Item> items;
    private boolean isValidated;

    public Transaction(int transactionID, List<Item> items) {
        this.transactionID = transactionID;
        this.items = items;
        this.isValidated = false;
    }

    public double totalPrice() {
        double total = 0;
        for(Item i: items) {
            total += i.price;
        }

        return total;
    }

    public double totalWeight() {
        double total = 0;
        for(Item i: items) {
            total += i.weight;
        }

        return total;
    }

    public boolean setValidated(boolean v) {
        this.isValidated = v;
        return true;
    }

    public boolean getValidated() {
        return this.isValidated;
    }

    public boolean setTransactionId(int v) {
        this.transactionID = v;
        return true;
    }

    public int getTransactionId() {
        return this.transactionID;
    }

    @Override
    public String toString() {
        String result = "TransactionID = " + transactionID + "\n";
        for(int i = 0; i < items.size(); i++) {
            result += items.get(i) + "\n";
        }
        return result;
    }
}