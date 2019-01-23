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

/**
 * @author Esben (s172986), August (s144461)
 */
public class TokenService {

    private Client client = ClientBuilder.newClient();
    private WebTarget r = client.target(WebEndpoints.BASETOKEN.url);

    /**
     * @author Esben (s172986)
     * @param cprNumber
     * @param numberOfTokens
     * @return
     */
    public Response requestTokens(String cprNumber, int numberOfTokens) {
        // Build request body object
        TokenRequest body = new TokenRequest(cprNumber, numberOfTokens);

        // Submit request
        Response response = r
          .path("token")
          .request()
          .post(Entity.entity(body, MediaType.APPLICATION_JSON));

        return response;
    }

    /**
     * @author Esben (s172986)
     * @param barcodeFilePath
     * @return
     */
    public Response getBarcodeImage(String barcodeFilePath) {
        Response response = r.path(barcodeFilePath)
                .request()
                .accept("image/png")
                .get(Response.class);

        return response;
    }

    /**
     * @author August (s144461)
     * @param tokenId
     * @return response
     */
    public Response getTokenById(String tokenId){
        return r.path("token/" + tokenId)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);
    }

    /**
     * @author August (s144461)
     * @param tokenId
     * @return response
     */
    public Response deleteToken(String tokenId) {
        return r.path("token/" + tokenId)
                .request()
                .delete(Response.class);
    }
}
