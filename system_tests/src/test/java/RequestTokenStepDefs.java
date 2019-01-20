import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import customer.networking.services.CustomerService;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class RequestTokenStepDefs {

  private String CPRNumber;
  private Response response;


  @Given("^a registered customer with the CPR \"([^\"]*)\"$")
  public void aRegisteredCustomerWithTheCPR(String CPRNumber) throws Throwable {
    this.CPRNumber = CPRNumber;

    CustomerService customerService = new CustomerService();
    this.response = customerService.verifyCustomer(CPRNumber);

    assertEquals(this.response.getStatus(), 200);
  }

  @When("^the customer submits a request for (\\d+) token$")
  public void theCustomerSubmitsARequestForToken(int arg0) {

  }

  @Then("^(\\d+) token is generated and stored$")
  public void tokenIsGeneratedAndStored(int arg0) {

  }

  @And("^the customer receives (\\d+) token containing an ID and a barcode URL$")
  public void theCustomerReceivesTokenContainingAnIDAndABarcodeURL(int arg0) {
  }
}
