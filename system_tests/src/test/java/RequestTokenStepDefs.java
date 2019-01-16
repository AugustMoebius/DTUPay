import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

public class RequestTokenStepDefs {
  @Given("^that a user is registered as a customer$")
  public void thatAUserIsRegisteredAsACustomer() throws PendingException {
    assertTrue(true);
    throw new PendingException();
  }

  @When("^the user requests (\\d+) token$")
  public void theUserRequestsToken(int arg0) throws PendingException {
    throw new PendingException();
  }

  @Then("^the user receives (\\d+) token containing an ID and a barcode URL$")
  public void theUserReceivesTokenContainingAnIDAndABarcodeURL(int arg0) throws PendingException {
    throw new PendingException();
  }
}
