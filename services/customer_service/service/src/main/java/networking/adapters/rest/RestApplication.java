package networking.adapters.rest;

import data.InMemoryDataSource;
import registation.CustomerRegistration;
import service.CustomerService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author Sarah (s153659)
 */
@ApplicationPath("/")
public class RestApplication extends Application {
    public static CustomerService customerService = new CustomerService(
            InMemoryDataSource.getInstance(),
            new CustomerRegistration()
    );
}

