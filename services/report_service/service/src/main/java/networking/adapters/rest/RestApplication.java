package networking.adapters.rest;

import data.MockDatabase;
import service.ReportService;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("/")
public class RestApplication {
    public RestApplication(){super();}
    /**
     * @author Sarah (s153659)
     */
    public static ReportService reportService = new ReportService(
            MockDatabase.getInstance()
    );

}
