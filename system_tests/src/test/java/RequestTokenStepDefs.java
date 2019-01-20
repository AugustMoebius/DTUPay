import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import customer.networking.services.CustomerService;
import gherkin.deps.com.google.gson.Gson;
import token.networking.requests.TokenRequest;
import token.networking.response.TokenResponse;
import token.networking.services.TokenService;

import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.Assert.*;

public class RequestTokenStepDefs {

  private String cprNumber;
  private Response response;
  private TokenResponse tokenResponse;


  @Given("^a registered customer with the CPR \"([^\"]*)\"$")
  public void aRegisteredCustomerWithTheCPR(String cprNumber) throws Throwable {
    this.cprNumber = cprNumber;

    /*
    CustomerService customerService = new CustomerService();
    this.response = customerService.verifyCustomer(CPRNumber);

    assertEquals(this.response.getStatus(), 200);
    */
  }

  @When("^the customer submits a request for (\\d+) token$")
  public void theCustomerSubmitsARequestForToken(int count) {
      throw new PendingException();
    //TokenService tokenService = new TokenService();
    //this.response = tokenService.requestTokens(count, this.cprNumber);
  }

  @Then("^customer receives (\\d+) token containing an ID and a barcode URL$")
  public void customerReceivesTokenContainingAnIDAndABarcodeURL(int count) {
      throw new PendingException();
    //assertEquals(200, this.response.getStatus());

    /*
    String jsonResponse = String.valueOf(this.response.getEntity());

    Gson gson = new Gson();
    this.tokenResponse = gson.fromJson(jsonResponse, TokenResponse.class);
    assertTrue(this.tokenResponse != null);
    */
  }
}
