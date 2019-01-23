package networking.adapters.rest;

import data.MockDatabase;
import service.ReportService;

import javax.ws.rs.ApplicationPath;

/**
 * @author Sarah (s153659)
 */
@ApplicationPath("/")
public class RestApplication {
    public RestApplication(){super();}

    public static ReportService reportService = new ReportService(
            MockDatabase.getInstance()
    );

}
