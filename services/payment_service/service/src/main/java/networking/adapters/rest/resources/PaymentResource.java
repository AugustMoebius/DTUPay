package networking.adapters.rest.resources;

import networking.adapters.rest.RestApplication;
import networking.adapters.rest.requests.PaymentRequest;
import service.exceptions.InvalidPaymentAmountException;

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
    System.out.println("POST: Payment submit: " + req.getPaymentAmount() +", " + req.getMerchantId() + ", " + req.getTokenId());
    // Handle payment request
    try {
      RestApplication.paymentService.submitPaymentRequest(req);
    } catch (InvalidPaymentAmountException e) {
      e.printStackTrace();
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    return Response.ok().build();
  }
}
