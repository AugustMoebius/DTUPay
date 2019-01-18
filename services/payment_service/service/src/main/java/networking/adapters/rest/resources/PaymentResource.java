package networking.adapters.rest.resources;

import networking.adapters.rest.RestApplication;
import networking.adapters.rest.requests.PaymentRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("payment")
public class PaymentResource {
  @GET
  public Response ping() {
    return Response.ok("Payment request").build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response submitPayment(PaymentRequest req) {
    System.out.println("Printing payment request contents");

    // Handle payment request
    RestApplication.paymentService.submitPaymentRequest(req);
    return Response.ok().build();
  }
}
