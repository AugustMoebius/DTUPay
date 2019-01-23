package report.networking.response;

import java.util.List;

/**
 * @author Emilie (s153762)
 */
public class ReportResponse {
    private String id;
    private List<String> transactionList;

    public ReportResponse(String id, List<String> transactionList){
        this.id = id;
        this.transactionList = transactionList;
    }

    public String getId() {
        return id;
    }

    public List<String> getTransactionList() {
        return transactionList;
    }
}
