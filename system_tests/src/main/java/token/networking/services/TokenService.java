package token.networking.services;

import payment.networking.WebEndpoints;
import payment.networking.requests.PaymentRequest;
import token.networking.requests.TokenRequest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TokenService {

    private Client client = ClientBuilder.newClient();
    private WebTarget r = client.target(WebEndpoints.BASETOKEN.url);

    public Response requestTokens(int count, String cprNumber) {
        // Build request body object
        TokenRequest body = new TokenRequest(count, cprNumber);

        // Submit request
        Response response = r.path("token").request().post(
                Entity.entity(body, MediaType.APPLICATION_JSON),
                Response.class
        );

        return response;
    }
}
