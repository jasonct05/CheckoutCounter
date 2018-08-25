package Model.Network;

import Util.Item;
import Util.Transaction;

import java.util.List;
import java.util.ArrayList;

public class DummyTransactionQuery implements ITransactionQuery {
    Transaction dummyTransaction;

    public DummyTransactionQuery() {
        List<Item> transactionItems = new ArrayList<Item>();
        transactionItems.add(new Item("DUMMY-ISBN-1", "xbox", 250, 5.0));
        transactionItems.add(new Item("DUMMY-ISBN-3", "bleach", 5, 1.0));

        this.dummyTransaction = new Transaction(1, transactionItems);
    }

    public Transaction QueryTransactionId(String queryUrl) {
        return this.dummyTransaction;
    }
}
