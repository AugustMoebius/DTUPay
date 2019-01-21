package networking.adapters.rest.resources;

import exceptions.InvalidCprException;
import networking.adapters.rest.requests.TokenRequest;
import networking.adapters.rest.responses.TokenResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static networking.adapters.rest.RestApplication.tokenService;

@Path("token")
public class TokenResource {

  @GET
  public Response ping() {
    return Response.ok("Token request").build();
  }

  /**
   * @author Esben Løvendal Kruse (s172986)
   * @param tokenRequest
   * @return
   * @throws InvalidCprException
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public TokenResponse requestToken(TokenRequest tokenRequest) throws InvalidCprException {
    // Handle token request
    TokenResponse tokenResponse = tokenService.handleTokenRequests(tokenRequest);

    System.out.println("Printing token request contents");
    System.out.println(tokenResponse);

    return tokenResponse;
  }
}
