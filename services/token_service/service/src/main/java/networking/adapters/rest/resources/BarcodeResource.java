package networking.adapters.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;

/**
 * @author Esben s172986, August s144461
 */
@Path("barcode")
public class BarcodeResource {

    /**
     * @author Esben (s172986)
     * @return response
     */
    @GET
    public Response pingBarcode() {
        return Response.ok("Barcode request").build();
    }

    /**
     * @author August (s144461)
     * @param barcodePath
     * @return
     */
    @GET
    @Path("/{barcodePath}")
    @Produces("image/png")
    public Response GetBarcode(@PathParam("barcodePath") String barcodePath) {
        File file = new File(barcodePath);

        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition",
                "attachment; filename=" + barcodePath);

        return response.build();
    }
}
