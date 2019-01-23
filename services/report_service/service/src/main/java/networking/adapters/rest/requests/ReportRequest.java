package networking.adapters.rest.requests;

/**
 * @author Sarah (s153659)
 */
public class ReportRequest {
    private String id;
    private String startDate;
    private String endDate;

    /**
     * @author Sarah (s153659)
     * @param id the Id of the user; can be customer, merchant or manager
     * @param startDate the start date
     * @param endDate the end date
     */
    public ReportRequest(String id, String startDate, String endDate){
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() { return id; }

    public String getStartDate() { return startDate; }

    public String getEndDate() { return endDate; }
}
