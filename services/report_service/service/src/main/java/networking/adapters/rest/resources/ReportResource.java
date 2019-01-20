package networking.adapters.rest.resources;

import networking.adapters.rest.RestApplication;
import networking.adapters.rest.requests.ReportRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("report")
public class ReportResource {

    @GET
    public Response ping() {
        return Response.ok("Token request").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response requestToken(ReportRequest reportRequest) {
        System.out.println("Printing payment request contents");

        // Handle report request
        RestApplication.reportService.generateReport(reportRequest.getId(), reportRequest.getPreviousDate(), reportRequest.getAfterDate());
        return Response.ok().build();
    }
}
