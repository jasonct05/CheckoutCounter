import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
public class TransactionProcessingModel {
    private Transaction transaction;
    private ITransactionQuery transactionQuery;

    public TransactionProcessingModel(boolean isDummy) {
        if (isDummy) {
            this.transactionQuery = new DummyTransactionQuery();
        } else {
            throw new NotImplementedException();
        }

        this.transaction = null;
    }

    public void queryTransaction(int transactionId) {
        if (transactionId < 0 || transaction != null)
            throw new IllegalArgumentException("Active Unfinished Transaction in Progress");
        this.transaction = this.transactionQuery.QueryTransactionId(transactionId);
    }

    public void validateTransaction(int transactionId){
        if(this.transaction == null || transactionId != this.transaction.transactionID) {
            throw new IllegalArgumentException();
        }

        // TODO:
        // weight check
        // image recognition shit
    }
}

class Transaction {
    public int transactionID; // primary key
    public List<Item> items;
    public boolean isValidated;

    public Transaction(int transactionID, List<Item> items) {
        this.transactionID = transactionID;
        this.items = items;
        this.isValidated = false;
    }
}

class Item {
    public String ISBN10;
    public String name;
    public int price;
    public double weight;

    public Item(String ISBN10, String name, int price, double weight) {
        this.ISBN10 = ISBN10;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }
}