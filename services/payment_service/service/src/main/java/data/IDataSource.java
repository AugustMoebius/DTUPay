package data;

import domain.Transaction;

public interface IDataSource {
    Transaction getTransaction(String tokenId);

    void updateTransaction(Transaction transaction);
}
