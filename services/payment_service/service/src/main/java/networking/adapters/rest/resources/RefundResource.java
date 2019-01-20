package networking.adapters.rest.resources;

import networking.adapters.rest.RestApplication;
import networking.adapters.rest.requests.PaymentRequest;
import networking.adapters.rest.requests.RefundRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author August
 */
@Path("refund")
public class RefundResource {
    @GET
    public Response ping() {
        return Response.ok("Refund request").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response submitRefund(RefundRequest req) {
        // Handle refund request
        RestApplication.paymentService.refundPayment(req.getTokenId());
        return Response.ok().build();
    }
}