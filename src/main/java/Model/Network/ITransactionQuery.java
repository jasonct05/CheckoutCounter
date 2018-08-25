package Model.Network;

import Util.Transaction;

public interface ITransactionQuery {
    Transaction QueryTransactionId(String queryUrl);
}
