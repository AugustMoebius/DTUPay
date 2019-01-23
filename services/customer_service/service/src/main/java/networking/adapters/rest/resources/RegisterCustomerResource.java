package networking.adapters.rest.resources;

import networking.adapters.rest.RestApplication;
import networking.adapters.rest.requests.RegisterCustomerRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Emilie (s153762), Sarah (s153659)
 */
@Path("customer")
public class RegisterCustomerResource {
    /**
     * @author Emilie (s153762)
     * @return Response
     */
    @GET
    public Response ping() {
        return Response.ok("customer registration request").build();
    }

    /**
     * This method consumes a post request and registers the customer.
     * @author Sarah (s153659)
     * @param req an instance of the RegisterCustomerRequest class
     * @return Response
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerCustomer(RegisterCustomerRequest req){
        System.out.println("POST: register customer");
        System.out.println(req.getFirstName()+" "+req.getLastName()+", "+req.getCpr());

        RestApplication.customerService.registerCustomer(req);

        return Response.ok().build();
    }
}
