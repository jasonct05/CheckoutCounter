import java.util.List;

public interface ITransactionQuery {
    Transaction QueryTransactionId(int transactionId);
    boolean validateTransaction(int transactionId);
}
