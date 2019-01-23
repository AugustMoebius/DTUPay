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
    /**
     * This method is for test purposes, so you can see the response on localhost
     * @author August
     * @return response
     */
    @GET
    public Response ping() {
        return Response.ok("Refund request").build();
    }

    /**
     * This method consumes a post request and sends the call to payment service.
     * @author August
     * @param req
     * @return response
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response submitRefund(RefundRequest req) {
        // Handle refund request
        RestApplication.paymentService.refundPayment(req.getTokenId());
        return Response.ok().build();
    }
}