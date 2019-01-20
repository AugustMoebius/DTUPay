import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import token.networking.response.TokenResponse;
import token.networking.services.TokenService;

import static org.junit.Assert.assertTrue;

public class RequestTokenStepDefs {

  private String cprNumber;


  /**
   * @author Esben Løvendal Kruse (s172986)
   * @param cprNumber
   * @throws Throwable
   */
  @Given("^a registered customer with the CPR \"([^\"]*)\"$")
  public void aRegisteredCustomerWithTheCPR(String cprNumber) throws Throwable {
    this.cprNumber = cprNumber;

    /*
    CustomerService customerService = new CustomerService();
    this.response = customerService.verifyCustomer(CPRNumber);

    assertEquals(this.response.getStatus(), 200);
    */
  }

  /**
   * @author Esben Løvendal Kruse (s172986)
   * @param numberOfTokens
   */
  @When("^the customer submits a request for (\\d+) token/s$")
  public void theCustomerSubmitsARequestForToken(int numberOfTokens) {
    TokenService tokenService = new TokenService();
    TokenResponse tokenResponse = tokenService.requestTokens(this.cprNumber, numberOfTokens);

    assertTrue(tokenResponse.getBarcodes().size() > 0);

      //throw new PendingException();
  }

  /**
   * @author Esben Løvendal Kruse (s172986)
   * @param numberOfTokens
   */
  @Then("^customer receives (\\d+) token/s containing an ID and a barcode URL$")
  public void customerReceivesTokenContainingAnIDAndABarcodeURL(int numberOfTokens) {
      throw new PendingException();
    //assertEquals(200, this.response.getStatus());
  }
}
