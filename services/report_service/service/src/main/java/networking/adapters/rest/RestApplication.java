package networking.adapters.rest;

import data.MockDatabase;
import service.ReportService;

import javax.ws.rs.ApplicationPath;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@ApplicationPath("/")
public class RestApplication {
    public static ReportService reportService = new ReportService(
            MockDatabase.getInstance()
    );

}
