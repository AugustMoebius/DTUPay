package data;

import domain.Transaction;

import java.util.HashMap;


public class MockDatabase implements IDataSource {
    private static final MockDatabase mockDatabase = new MockDatabase();
    private HashMap<String, Transaction> allTransactions;
    public MockDatabase(){
        allTransactions = new HashMap<String, Transaction>();
    }

    public static MockDatabase getInstance() {
        return mockDatabase;
    }
    
}
