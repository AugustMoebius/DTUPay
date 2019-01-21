package networking.adapters.rest.resources;

import networking.adapters.rest.requests.RegisterMerchantRequest;
import networking.adapters.rest.RestApplication;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("merchant")
public class RegisterMerchantResource {
    /**
     * @author Sarah
     * @return Response
     */
    @GET
    public Response ping() {
        return Response.ok("merchant registration request").build();
    }

    /**
     * @author Sarah
     * @param req
     * @return Response
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerMerchant(RegisterMerchantRequest req){
        System.out.println("POST: register merchant");

        RestApplication.merchantService.registerMerchant(req);

        return Response.ok().build();
    }

}
