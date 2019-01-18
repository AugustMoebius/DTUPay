package networking.adapters.rest.resources;

import networking.adapters.rest.requests.RegisterCustomerRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("register_customer")
public class RegisterCustomerResource {
    /**
     * @author Emilie
     * @return Response
     */
    @GET
    public Response ping() {
        return Response.ok("Customer registration request").build();
    }

    /**
     * @author Sarah
     * @param req
     * @return Response
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerCustomer(RegisterCustomerRequest req){
        System.out.println("POST: register customer");
        return Response.ok("hej").build();
    }
}
