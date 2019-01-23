package networking.adapters.rest.requests;


public class ReportRequest {
    private String id;
    private String startDate;
    private String endDate;

    /**
     * @author Sarah
     * @param id
     * @param startDate
     * @param endDate
     */
    public ReportRequest(String id, String startDate, String endDate){
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @author Sarah
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @author Sarah
     * @return startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @author Sarah
     * @return endDate
     */
    public String getEndDate() {
        return endDate;
    }
}
