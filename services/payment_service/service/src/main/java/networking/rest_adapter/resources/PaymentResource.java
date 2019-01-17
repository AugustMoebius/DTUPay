package networking.rest_adapter.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/payment")
public class PaymentResource {
  @POST
  public Response submitPayment() {
    return Response.status(404).build();
  }
}
