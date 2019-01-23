package networking.adapters.rest.resources;

import networking.adapters.rest.RestApplication;
import networking.adapters.rest.requests.ReportRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Sarah (s153659)
 */
@Path("report")
public class ReportResource {
    /**
     * This method is for testing purposes that you will be able to see if the request has gone through on localhost.
     * @author Sarah (s153659)
     * @return Response
     */
    @GET
    public Response ping() {
        return Response.ok("Report request").build();
    }

    /**
     * This method consumes a post request and calls reportService to generate a report.
     * @author Sarah (s153659)
     * @param reportRequest an instance of the ReportRequest class
     * @return Response
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response requestReport(ReportRequest reportRequest) {
        System.out.println("Printing report request contents");

        // Handle report request
        RestApplication.reportService.generateReport(null, reportRequest.getId(), reportRequest.getStartDate(), reportRequest.getEndDate());
        return Response.ok().build();
    }
}
