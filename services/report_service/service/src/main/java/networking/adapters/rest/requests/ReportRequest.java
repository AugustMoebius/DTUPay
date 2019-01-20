package networking.adapters.rest.requests;


public class ReportRequest {
    private String id;
    private String previousDate;
    private String afterDate;

    /**
     * @author Sarah
     * @param id
     * @param previousDate
     * @param afterDate
     */
    public ReportRequest(String id, String previousDate, String afterDate){
        this.id = id;
        this.previousDate = previousDate;
        this.afterDate = afterDate;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return previousDate
     */
    public String getPreviousDate() {
        return previousDate;
    }

    /**
     *
     * @return afterDate
     */
    public String getAfterDate() {
        return afterDate;
    }
}
