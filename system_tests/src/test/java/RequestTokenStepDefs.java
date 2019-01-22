import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import services.CprService;
import token.networking.response.TokenBarcodePair;
import token.networking.response.TokenGeneratedResponse;
import token.networking.services.TokenService;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestTokenStepDefs {
  private String cprNumber;
  private TokenGeneratedResponse existingTokens;
  private TokenGeneratedResponse generatedTokens;
  private TokenService tokenService;

  private Response tokenGeneratedResponse;


  public RequestTokenStepDefs() {
    this.tokenService = new TokenService();
  }

  @After("@tagToken")
  public void after() {
    // Extract tokens from response
    this.deleteTokensFromResponse(this.generatedTokens);
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
   */
  @Given("^a registered customer$")
  public void aRegisteredCustomerWithTheCPR() {
    this.cprNumber = CprService.generateCpr();
  }

  /**
   * @author Esben Løvendal Kruse (s172986)
   * @param numberOfTokens
   */
  @When("^the customer submits a request for (-?\\d+) token/s$")
  public void theCustomerSubmitsARequestForToken(int numberOfTokens) {
    this.tokenGeneratedResponse = tokenService.requestTokens(this.cprNumber, numberOfTokens);
  }

  @Then("^the request succeeds$")
  public void theRequestSucceeds() {
    // Assertion on status code
    assertEquals(200, this.tokenGeneratedResponse.getStatus());
  }

  /**
   * @author Esben Løvendal Kruse (s172986)
   * @param numberOfTokens
   */
  @Then("^customer receives (\\d+) token/s containing an ID and a barcode URL$")
  public void customerReceivesTokenContainingAnIDAndABarcodeURL(int numberOfTokens) {
    this.generatedTokens = this.tokenGeneratedResponse.readEntity(TokenGeneratedResponse.class);

    assertEquals(numberOfTokens, this.generatedTokens.getTokenBarcodePairs().size());
  }

  @And("^customer can access the barcode via the URL$")
  public void customerCanAccessTheBarcodeViaTheURL() {
    for (TokenBarcodePair pair : this.generatedTokens.getTokenBarcodePairs()) {
      assertEquals(200, this.tokenService.getBarcodeImage(pair.getBarcodeRelativePath()).getStatus());
    }
  }

  @And("^that customer has already been assigned (\\d+) token$")
  public void thatCustomerHasAlreadyBeenAssignedToken(int numberOfTokens) {
    // Request tokens to assign to user ahead of main request.
    this.existingTokens = tokenService
      .requestTokens(this.cprNumber, numberOfTokens)
      .readEntity(TokenGeneratedResponse.class);
  }

  // ERROR SCENARIOS

  @Then("^the customer receives an error response$")
  public void theCustomerReceivesAnErrorResponse() {
    assertEquals(400, this.tokenGeneratedResponse.getStatus());
  }

  @And("^the response contains the error message \"([^\"]*)\"$")
  public void theResponseContainsTheErrorMessage(String expErrorMsg) {
    String actErrorMsg = this.tokenGeneratedResponse.readEntity(String.class);

    assertEquals(expErrorMsg, actErrorMsg);
  }
}
