import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import customer.networking.services.CustomerService;
import services.CprService;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class RegisterCustomerStepdefs {
    private String firstName;
    private String lastName;
    private String cpr;
    private Response response;

    /**
     *
     * @param firstName
     * @param lastName
     * @param cpr
     * @author Emilie
     */
    @Given("^a name \"([^\"]*)\" \"([^\"]*)\"$")
    public void aNameAndCPR(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.cpr = CprService.generateCpr();
    }

    /**
     * @author Sarah
     */
    @When("^registering a customer$")
    public void registeringACustomer() {
        CustomerService customerService = new CustomerService();
        this.response = customerService.registerCustomer(firstName, lastName, cpr);
    }

    /**
     *
     * @throws Throwable
     * @author Emilie
     */
    @Then("^a new customer is added to DTUPay$")
    public void aNewCustomerIsAddedToDTUPay() {
//        throw new PendingException();
        assertEquals(200, response.getStatus());
    }
}