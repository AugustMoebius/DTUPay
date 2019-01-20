package report.networking.requests;

public class ReportRequest {
    private String id;
    private String previousDate;
    private String afterDate;

    public ReportRequest(String id, String previousDate, String afterDate){
        this.id = id;
        this.previousDate = previousDate;
        this.afterDate = afterDate;
    }

    public String getId() {
        return id;
    }

    public String getPreviousDate() {
        return previousDate;
    }

    public String getAfterDate() {
        return afterDate;
    }
}
