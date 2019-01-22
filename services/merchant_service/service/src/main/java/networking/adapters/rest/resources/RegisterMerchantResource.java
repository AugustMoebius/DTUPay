package networking.adapters.rest.resources;

import exceptions.MerchantRegistrationException;
import networking.adapters.rest.requests.RegisterMerchantRequest;
import networking.adapters.rest.RestApplication;

import javax.ws.rs.*;
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

        try {
            RestApplication.merchantService.registerMerchant(req);
        } catch (MerchantRegistrationException e){
            //throw new BadRequestException(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }

        return Response.ok("ok").build();
    }

}
