import java.util.List;
import java.util.ArrayList;

public class DummyTransactionQuery implements ITransactionQuery {
    Transaction dummyTransaction;

    public DummyTransactionQuery() {
        List<Item> transactionItems = new ArrayList<Item>();
        transactionItems.add(new Item("DUMMY-ISBN-1", "XBOX One", 250, 10.0));
        transactionItems.add(new Item("DUMMY-ISBN-2", "Lysol Bleach", 10, 1.0));
        transactionItems.add(new Item("DUMMY-ISBN-3", "Coke", 1, 0.5));

        this.dummyTransaction = new Transaction(1, transactionItems);
    }

    public Transaction QueryTransactionId(String queryUrl) {
        return this.dummyTransaction;
    }

    public boolean validateTransaction(String queryUrl) {
        return true;
    }
}
