package report.networking.services;

import payment.networking.WebEndpoints;
import report.networking.requests.ReportRequest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Sarah (s153659)
 */
public class ReportService {
    private Client client = ClientBuilder.newClient();
    private WebTarget r = client.target(WebEndpoints.BASEREPORT.url);

    public Response requestReport(String id, String previousDate, String afterDate) {
        // Build request body object
        ReportRequest body = new ReportRequest(id, previousDate, afterDate);

        // Submit request
        Response response = r.path("report").request().post(
                Entity.entity(body, MediaType.APPLICATION_JSON),
                Response.class
        );

        return response;
    }
}
