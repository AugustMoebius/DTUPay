package customer.networking.services;

import customer.networking.requests.RegisterCustomerRequest;
import payment.networking.WebEndpoints;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Sarah (s153659), Esben (s172986)
 */
public class CustomerService {
    private Client client = ClientBuilder.newClient();
    private WebTarget r = client.target(WebEndpoints.BASECUSTOMER.url);

    /**
     * @author Sarah (s153659)
     * @param firstName
     * @param lastName
     * @param cpr
     * @return response
     */
    public Response registerCustomer(String firstName, String lastName, String cpr) {
        // Build request body object
        RegisterCustomerRequest body = new RegisterCustomerRequest(firstName, lastName, cpr);

        // Submit request
        Response response = r.path("customer").request().post(
                Entity.entity(body, MediaType.APPLICATION_JSON),
                Response.class
        );
        return response;
    }

    /**
     * @author Esben (s172986)
     * @param cpr
     * @return response
     */
    public Response verifyCustomer(String cpr) {
        // Get request
        Response response = r.path("customer/" + cpr)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);

        return response;
    }
}
