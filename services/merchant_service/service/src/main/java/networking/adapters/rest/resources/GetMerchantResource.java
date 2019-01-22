package networking.adapters.rest.resources;

import management.domain.Merchant;
import networking.adapters.rest.RestApplication;
import networking.adapters.rest.responses.GetMerchantResponse;
import org.apache.tools.ant.taskdefs.Get;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("merchant/{id}")
public class GetMerchantResource {
    /**
     * @author Sarah
     * @return Response
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public GetMerchantResponse getMerchant(@PathParam("id") String id){
        System.out.println("GET: get merchant");
        return RestApplication.merchantService.getMerchant(id);
    }


}
