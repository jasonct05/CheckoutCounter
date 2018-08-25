import java.util.List;

public interface ITransactionQuery {
    Transaction QueryTransactionId(String queryUrl);
    boolean validateTransaction(String queryUrl);
}
