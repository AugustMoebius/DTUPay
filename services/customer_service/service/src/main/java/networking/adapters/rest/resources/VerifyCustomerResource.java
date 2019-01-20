package networking.adapters.rest.resources;

import networking.adapters.rest.RestApplication;
import registation.domain.CPRNumber;
import registation.exceptions.CustomerNotFoundException;
import registation.exceptions.InvalidCprException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("customer")
public class VerifyCustomerResource {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response verifyCustomer(String cprNumber) throws InvalidCprException, CustomerNotFoundException {

        System.out.println("GET: Verify customer");
        System.out.println(cprNumber);

        RestApplication.customerService.getCustomer(new CPRNumber(cprNumber));

        return Response.ok().build();
    }
}
