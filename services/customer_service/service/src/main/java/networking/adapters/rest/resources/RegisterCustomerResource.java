package networking.adapters.rest.resources;

import networking.adapters.rest.RestApplication;
import networking.adapters.rest.requests.RegisterCustomerRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("customer")
public class RegisterCustomerResource {
    /**
     * @author Emilie
     * @return Response
     */
    @GET
    public Response ping() {
        return Response.ok("customer registration request").build();
    }

    /**
     * This method consumes a post request and registers the customer.
     * @author Sarah
     * @param req
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
