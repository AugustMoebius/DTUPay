package networking.adapters.rest.resources;

import exceptions.InvalidCprException;
import networking.adapters.rest.requests.TokenRequest;
import networking.adapters.rest.responses.TokenBarcodePair;
import networking.adapters.rest.responses.TokenGeneratedResponse;
import networking.adapters.rest.responses.TokenGetResponse;
import service.exceptions.TokenGenerationFailedException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

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
  public Response requestToken(TokenRequest tokenRequest) {
    // Handle token request
    TokenGeneratedResponse res;
    try {
      res = tokenService.handleTokenGenerateRequests(tokenRequest);
    } catch (TokenGenerationFailedException e) {
      return Response.status(Response.Status.BAD_REQUEST)
        .entity(e.getMessage()).build();
    }

    return Response.ok(res).build();
  }

  /**
   * @author Esben Løvendal Kruse (s172986)
   * @param id
   * @return
   */
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public TokenGetResponse GetToken(@PathParam("id") String id) {
    TokenGetResponse tokenGetResponse = tokenService.handleTokenGetRequests(id);

    return tokenGetResponse;
  }

  /**
   * @author August
   * @param id
   */
  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response DeleteToken(@PathParam("id") String id)  {
    tokenService.deleteToken(id);
    return Response.ok("Token deletion request").build();
  }
}
