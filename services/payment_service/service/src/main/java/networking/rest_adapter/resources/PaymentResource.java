package networking.rest_adapter.resources;

import networking.rest_adapter.requests.PaymentRequest;

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

    System.out.println(req.getMerchantId());
    System.out.println(req.getPaymentAmount());
    System.out.println(req.getTokenId());

    return Response.ok().build();
  }
}
