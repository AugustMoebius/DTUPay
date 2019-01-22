import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import merchant.networking.services.MerchantService;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

public class RegisterMerchantStepDefs {
  private String cvrNumber;
  private String firstName;
  private String lastName;
  private Response res;

  @Given("^a merchant with the CVR number \"([^\"]*)\", with the name \"([^\"]*)\" \"([^\"]*)\"$")
  public void aMerchantWithTheCVRNumberWithTheName(String cvrNumber, String firstName, String lastName) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    this.cvrNumber = cvrNumber;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @And("^the merchant has an account in the DTU bank$")
  public void theMerchantHasAnAccountInTheDTUBank() {
    throw new PendingException();
  }


  @When("^the merchant submits a request to register$")
  public void theMerchantSubmitsARequestToRegister() {
    // Build request object
    //throw new PendingException();

    MerchantService ms = new MerchantService();
    this.res = ms.registerMerchant(this.firstName, this.lastName, this.cvrNumber);
  }


  @Then("^the registration submission succeeds$")
  public void theRegistrationSubmissionSucceeds() {
    //throw new PendingException();

    assertEquals(200, this.res.getStatus());
  }


  @And("^the merchant is now registered with the name \"([^\"]*)\" \"([^\"]*)\", and the CVR number \"([^\"]*)\"$")
  public void theMerchantIsNowRegisteredWithTheNameAndTheCVRNumber(String firstName, String lastName, String cvrNumber) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }
}
