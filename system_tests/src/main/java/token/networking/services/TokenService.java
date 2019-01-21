package token.networking.services;

import payment.networking.WebEndpoints;
import token.networking.requests.TokenRequest;
import token.networking.response.TokenGeneratedResponse;
import token.networking.response.TokenResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class TokenService {

    private Client client = ClientBuilder.newClient();
    private WebTarget r = client.target(WebEndpoints.BASETOKEN.url);

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param cprNumber
     * @param numberOfTokens
     * @return
     */
    public TokenGeneratedResponse requestTokens(String cprNumber, int numberOfTokens) {
        // Build request body object
        TokenRequest body = new TokenRequest(cprNumber, numberOfTokens);

        // Submit request
        TokenGeneratedResponse response = r.path("token").request().post(
                Entity.entity(body, MediaType.APPLICATION_JSON),
                TokenGeneratedResponse.class
        );

        return response;
    }

    /**
     * @author August
     * @param tokenId
     * @return
     */
    public Response getTokenById(String tokenId){
        return r.path("token/" + tokenId)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);
    }
}
