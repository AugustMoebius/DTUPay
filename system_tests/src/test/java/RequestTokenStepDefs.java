import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import token.networking.response.TokenBarcodePair;
import token.networking.response.TokenGeneratedResponse;
import token.networking.services.TokenService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestTokenStepDefs {

  private String cprNumber;
  private TokenGeneratedResponse tokenGeneratedResponse;
  private TokenService tokenService;


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
    this.tokenService = new TokenService();
    this.tokenGeneratedResponse = tokenService.requestTokens(this.cprNumber, numberOfTokens);
  }

  /**
   * @author Esben Løvendal Kruse (s172986)
   * @param numberOfTokens
   */
  @Then("^customer receives (\\d+) token/s containing an ID and a barcode URL$")
  public void customerReceivesTokenContainingAnIDAndABarcodeURL(int numberOfTokens) {
    assertEquals(numberOfTokens, this.tokenGeneratedResponse.getTokenBarcodePairs().size());
  }

  @And("^customer can access the barcode via the URL$")
  public void customerCanAccessTheBarcodeViaTheURL() {
    for (TokenBarcodePair pair : this.tokenGeneratedResponse.getTokenBarcodePairs()) {
      assertEquals(200, this.tokenService.getBarcodeImage(pair.getBarcodeRelativePath()).getStatus());
    }
  }
}
