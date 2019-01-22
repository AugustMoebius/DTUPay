import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import customer.networking.requests.RegisterCustomerRequest;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;
import dtu.ws.fastmoney.User;
import merchant.networking.services.MerchantService;
import services.CvrService;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

public class RegisterMerchantStepDefs {
  private String cvrNumber;
  private String firstName;
  private String lastName;
  private User merchant;
  private Response res;
  private BankService bankService;

  public RegisterMerchantStepDefs() {
    this.bankService = new BankServiceService().getBankServicePort();
  }

  /**
   * @author Sarah
   * @param firstName
   * @param lastName
   * @throws Throwable
   */
  @Given("^a merchant with a CVR number and the name \"([^\"]*)\" \"([^\"]*)\"$")
  public void aMerchantWithACVRNumberAndTheName(String firstName, String lastName) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    this.firstName = firstName;
    this.lastName = lastName;

    this.cvrNumber = CvrService.generateCvr();
  }

  /**
   * @author Sebastian
   * @param cvrNumber
   * @param firstName
   * @param lastName
   * @throws Throwable
   */
  @Given("^a merchant with the invalid CVR number \"([^\"]*)\" and the name \"([^\"]*)\" \"([^\"]*)\"$")
  public void aMerchantWithTheInvalidCVRNumberAndTheName(String cvrNumber, String firstName, String lastName) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    this.firstName = firstName;
    this.lastName = lastName;

    this.cvrNumber = cvrNumber;
  }

  /**
   * @author Sarah
   * @param merchantBalance
   * @throws Throwable
   */
  @And("^the merchant has an account in the DTU bank with a balance at (\\d+)$")
  public void theMerchantHasAnAccountInTheDTUBankWithABalanceAt(int merchantBalance) throws Throwable {
    this.merchant = new User();
    this.merchant.setFirstName(firstName);
    this.merchant.setLastName(lastName);
    this.merchant.setCprNumber(cvrNumber);
    // Register the merchant in the bank
    try {
      this.bankService.createAccountWithBalance(merchant, new BigDecimal(merchantBalance));
    } catch (BankServiceException_Exception e) {
      String merchantAccountId = bankService.getAccountByCprNumber(this.merchant.getCprNumber()).getId();
      this.bankService.retireAccount(merchantAccountId);
      this.bankService.createAccountWithBalance(merchant, new BigDecimal(merchantBalance));
    }
  }

  /**
   * @author Sarah
   */
  @When("^the merchant submits a request to register$")
  public void theMerchantSubmitsARequestToRegister() {
    // Build request object

    //throw new PendingException();
    MerchantService ms = new MerchantService();
    this.res = ms.registerMerchant(this.firstName, this.lastName, this.cvrNumber);
  }

  /**
   * @author Sarah
   */
  @Then("^the registration submission succeeds$")
  public void theRegistrationSubmissionSucceeds() {
    //throw new PendingException();

    assertEquals(200, this.res.getStatus());
  }

  /**
   * @author Sarah
   * @throws Throwable
   */
  @Then("^the registration submission succeeds and the merchant is registered$")
  public void theRegistrationSubmissionSucceedsAndTheMerchantIsRegistered() throws Throwable {
    //throw new PendingException();
    assertTrue(200 <= this.res.getStatus() && this.res.getStatus() < 300);
  }

  /**
   * @author Sarah
   * @param errorMessage
   * @throws Throwable
   */
  @Then("^the merchant submission fails and he gets an error message \"([^\"]*)\"$")
  public void theMerchantSubmissionFailsAndHeGetsAnErrorMessage(String errorMessage) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    // Receives a 400 status code and a message in a body?
    assertEquals(400, this.res.getStatus());
    assertEquals(errorMessage, res.readEntity(String.class));
  }

  @Then("^the merchant submission fails and he gets an error message where _cvr_ is merchant CVR: \"([^\"]*)\"$")
  public void theMerchantSubmissionFailsAndHeGetsAnErrorMessageWhere_cvr_IsMerchantCVR(String errorMsg) {
    assertEquals(400, this.res.getStatus());

    // Build expected string
    String expErrorMsg = errorMsg.replace("_cvr_", this.cvrNumber);
    String actErrorMsg = this.res.readEntity(String.class);

    assertEquals(expErrorMsg, actErrorMsg);
  }


}
