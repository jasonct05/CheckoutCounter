package Model;

import Util.Transaction;

public interface ITransactionQuery {
    Transaction QueryTransactionId(String queryUrl);
    boolean validateTransaction(String queryUrl);
}
