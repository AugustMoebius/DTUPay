package networking.adapters.rest.resources;

import networking.adapters.rest.RestApplication;
import networking.adapters.rest.requests.GetMerchantRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("get-merchant")
public class GetMerchantResource {
    /**
     * @author Sarah
     * @return Response
     */
    @GET
    public Response ping() {
        return Response.ok("merchant get request").build();
    }

    /**
     * @author Sarah
     * @param req
     * @return Response
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getMerchant(GetMerchantRequest req){
        System.out.println("POST: get merchant");

        RestApplication.merchantService.getMerchant(req.getCvr());

        return Response.ok("ok").build();
    }


}
