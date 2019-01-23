package networking.adapters.rest.resources;

import networking.adapters.rest.RestApplication;
import registation.domain.CPRNumber;
import registation.exceptions.CustomerNotFoundException;
import registation.exceptions.InvalidCprException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("customer")
public class VerifyCustomerResource {

    /**
     * This method verifies a customer with a get request.
     * @author Esben
     * @param cprNumber
     * @return
     * @throws InvalidCprException
     * @throws CustomerNotFoundException
     */
    @GET
    @Path("/{id}")
    public Response verifyCustomer(@PathParam("id") String cprNumber) throws InvalidCprException, CustomerNotFoundException {
        System.out.println("GET: Verify customer");
        System.out.println(cprNumber);

        RestApplication.customerService.getCustomer(new CPRNumber(cprNumber));

        return Response.ok().build();
    }
}
