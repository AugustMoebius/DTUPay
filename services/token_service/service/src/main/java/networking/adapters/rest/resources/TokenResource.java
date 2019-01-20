package networking.adapters.rest.resources;

import networking.adapters.rest.RestApplication;
import networking.adapters.rest.requests.TokenRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("token")
public class TokenResource {

  @GET
  public Response ping() {
    return Response.ok("Token request").build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response requestToken(TokenRequest tokenRequest) {
    System.out.println("Printing payment request contents");

    // Handle token request
    RestApplication.tokenService.generateTokens(tokenRequest.getCount(), tokenRequest.getCprNumber());
    return Response.ok().build();
  }
}
