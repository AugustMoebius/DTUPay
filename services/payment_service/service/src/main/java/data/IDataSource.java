package data;

import domain.Transaction;
import networking.adapters.message_queue.PaymentVerifiedRequest;

/**
 * @author August
 */
public interface IDataSource {
    Transaction getTransaction(String tokenId);

    void updateTransaction(Transaction transaction);

    void addTransaction(PaymentVerifiedRequest req);
}
