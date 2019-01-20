package payment.networking.services;

import payment.networking.WebEndpoints;
import payment.networking.requests.PaymentRequest;
import payment.networking.requests.RefundRequest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PaymentService {
  private Client client = ClientBuilder.newClient();
  private WebTarget r = client.target(WebEndpoints.BASE.url);

  public Response submitPayment(String merchantId, int paymentAmount, String tokenId) {
    // Build request body object
    PaymentRequest body = new PaymentRequest(merchantId, paymentAmount, tokenId);

    // Submit request
    Response response = r.path("payment").request().post(
      Entity.entity(body, MediaType.APPLICATION_JSON),
      Response.class
    );

    return response;
  }

    public Response submitRefund(String tokenId) {
      // Build request body
      RefundRequest body = new RefundRequest(tokenId);

      //Submit request
      Response response = r.path("refund").request().post(
              Entity.entity(body,MediaType.APPLICATION_JSON),
              Response.class
      );

    return response;
    }
}
