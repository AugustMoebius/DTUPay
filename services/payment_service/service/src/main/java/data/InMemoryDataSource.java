package data;

import domain.Transaction;
import networking.adapters.message_queue.PaymentVerifiedRequest;

import java.util.HashMap;

public class InMemoryDataSource implements IDataSource {


    private static final InMemoryDataSource dataSource = new InMemoryDataSource();
    private HashMap<String, Transaction> transactions = new HashMap<>();

    /**
     * @author August (s144461)
     * @return
     */
    public static InMemoryDataSource getInstance(){
        return dataSource;
    }

    private InMemoryDataSource(){
    }

    /**
     * @author Emilie (s153762)
     * @param tokenId
     * @return transaction
     */
    @Override
    public Transaction getTransaction(String tokenId) {
        return transactions.get(tokenId);
    }

    /**
     * @author August (s144461)
     * @param transaction
     */
    // TODO: Consider add vs. update vs. put - Emilie (s153762)  August (s144461)
    @Override
    public void updateTransaction(Transaction transaction) {
        transactions.put(transaction.getTokenId(), transaction);
    }

    /**
     * @author Sebastian (s144071)
     * @param req
     */
    @Override
    public void addTransaction(PaymentVerifiedRequest req) {
        Transaction newTransaction = new Transaction(req.getMerchantId(),req.getPaymentAmount(),req.getTokenId(),req.getCprNumber());
        transactions.put(newTransaction.getTokenId(),newTransaction);
    }
}
