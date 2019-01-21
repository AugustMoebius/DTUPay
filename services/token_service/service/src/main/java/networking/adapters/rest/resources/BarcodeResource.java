package networking.adapters.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("barcode")
public class BarcodeResource {

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return
     */
    @GET
    public Response pingBarcode() {
        return Response.ok("Barcode request").build();
    }

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
