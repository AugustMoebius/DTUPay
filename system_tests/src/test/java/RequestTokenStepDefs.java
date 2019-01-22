import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import token.networking.response.TokenBarcodePair;
import token.networking.response.TokenGeneratedResponse;
import token.networking.services.TokenService;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RequestTokenStepDefs {

  private String cprNumber;
  private TokenGeneratedResponse existingTokens;
  private TokenGeneratedResponse tokenGeneratedResponse;
  private TokenService tokenService;

  public RequestTokenStepDefs() {
    this.tokenService = new TokenService();
  }

  @After("@tagToken")
  public void after() {
    this.deleteTokensFromResponse(this.tokenGeneratedResponse);
    this.deleteTokensFromResponse(this.existingTokens);
  }

  private void deleteTokensFromResponse(TokenGeneratedResponse res) {
    if (res == null) {
      return;
    }

    List<TokenBarcodePair> pairs = res.getTokenBarcodePairs();
    if (pairs == null) {
      return;
    }

    for (TokenBarcodePair tbp : pairs) {
      this.tokenService.deleteToken(tbp.getTokenId());
    }
  }

  /**
   * @author Esben Løvendal Kruse (s172986)
   * @param cprNumber
   * @throws Throwable
   */
  @Given("^a registered customer with the CPR \"([^\"]*)\"$")
  public void aRegisteredCustomerWithTheCPR(String cprNumber) {
    this.cprNumber = cprNumber;
  }

  /**
   * @author Esben Løvendal Kruse (s172986)
   * @param numberOfTokens
   */
  @When("^the customer submits a request for (\\d+) token/s$")
  public void theCustomerSubmitsARequestForToken(int numberOfTokens) {
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

  @And("^that customer has already been assigned (\\d+) token$")
  public void thatCustomerHasAlreadyBeenAssignedToken(int numberOfTokens) {
    // Request tokens to assign to user ahead of main request.
    this.existingTokens = tokenService.requestTokens(this.cprNumber, numberOfTokens);
  }
}
