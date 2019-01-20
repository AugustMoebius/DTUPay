package networking.adapters.rest;

import data.MockDatabase;
import service.ReportService;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class RestApplication {
    public static ReportService reportService = new ReportService(
            MockDatabase.getInstance()
    );

}
