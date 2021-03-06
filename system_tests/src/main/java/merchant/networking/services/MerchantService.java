package merchant.networking.services;

import merchant.networking.requests.RegisterMerchantRequest;
import payment.networking.WebEndpoints;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Emulate client side for REST requests to merchant service.
 * @author Sebastian (s144071), Sarah (s153659)
 */
public class MerchantService {
    private Client client = ClientBuilder.newClient();
    private WebTarget r = client.target(WebEndpoints.BASEMERCHANT.url);

    /**
     * This method sends a request to register a merchant.
     * @param firstName
     * @param lastName
     * @param cvr
     * @return response
     * @author Sebastian (s144071)
     */
    public Response registerMerchant(String firstName, String lastName, String cvr) {
        // Build request body object
        RegisterMerchantRequest body = new RegisterMerchantRequest(firstName, lastName, cvr);

        // Submit request
        Response response = r.path("merchant").request().post(
                Entity.entity(body, MediaType.APPLICATION_JSON),
                Response.class
        );

        return response;
    }

    /**
     * @author Sarah (s153659)
     * @param cvr
     * @return response
     */
    public Response getMerchant(String cvr){
        return r.path("merchant/" + cvr)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);
    }

}
