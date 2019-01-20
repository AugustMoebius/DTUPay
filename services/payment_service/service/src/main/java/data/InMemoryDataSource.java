package data;

import domain.Transaction;

import java.util.HashMap;

/**
 * @author : August, Sebastian
 */
public class InMemoryDataSource implements IDataSource {


    private static final InMemoryDataSource dataSource = new InMemoryDataSource();
    private HashMap<String, Transaction> transactions = new HashMap<>();

    public static InMemoryDataSource getInstance(){
        return dataSource;
    }

    private InMemoryDataSource(){
    }

    /**
     * @authour Emilie
     * @param tokenId
     * @return transaction
     */
    @Override
    public Transaction getTransaction(String tokenId) {
        return transactions.get(tokenId);
    }

    /**
     * @author August
     * @param transaction
     */
    // TODO: Consider add vs. update vs. put - Emilie  August
    @Override
    public void updateTransaction(Transaction transaction) {
        transactions.put(transaction.getTokenId(), transaction);
    }
}
