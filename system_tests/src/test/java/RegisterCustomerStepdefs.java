import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import customer.networking.services.CustomerService;
import services.CprService;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * @author Emilie (s153762), Sarah (s153659)
 */
public class RegisterCustomerStepdefs {
    private String firstName;
    private String lastName;
    private String cpr;
    private Response response;

    /**
     * @param firstName
     * @param lastName
     * @author Emilie (s153762)
     */
    @Given("^a name \"([^\"]*)\" \"([^\"]*)\"$")
    public void aNameAndCPR(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.cpr = CprService.generateCpr();
    }

    /**
     * @author Sarah (s153659)
     */
    @When("^registering a customer$")
    public void registeringACustomer() {
        CustomerService customerService = new CustomerService();
        this.response = customerService.registerCustomer(firstName, lastName, cpr);
    }

    /**
     * @author Emilie (s153762)
     */
    @Then("^a new customer is added to DTUPay$")
    public void aNewCustomerIsAddedToDTUPay() {
        assertEquals(200, response.getStatus());
    }
}