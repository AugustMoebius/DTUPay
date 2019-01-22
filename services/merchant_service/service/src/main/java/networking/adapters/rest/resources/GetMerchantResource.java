package networking.adapters.rest.resources;

import com.google.gson.Gson;
import management.exceptions.MerchantServiceException;
import networking.adapters.rest.RestApplication;
import networking.adapters.rest.responses.GetMerchantResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("merchant/{id}")
public class GetMerchantResource {
    /**
     * @author Sarah
     * @return Response
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getMerchant(@PathParam("id") String id){
        System.out.println("GET: get merchant "+id);
        GetMerchantResponse merchantResponse;
        try {
            merchantResponse = RestApplication.merchantService.getMerchant(id);
        } catch (MerchantServiceException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
        Gson gson = new Gson();
        String merchantResponseJson = gson.toJson(merchantResponse);

        return Response.status(Response.Status.ACCEPTED).entity(merchantResponseJson).build();
    }


}
