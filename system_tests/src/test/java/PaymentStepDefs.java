import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import customer.networking.services.CustomerService;
import dtu.ws.fastmoney.*;
import org.junit.Assert;
import payment.networking.services.PaymentService;
import token.networking.response.TokenBarcodePair;
import token.networking.response.TokenGeneratedResponse;
import token.networking.response.TokenResponse;
import token.networking.services.TokenService;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PaymentStepDefs {
  private final BankService bankService;
  private User customer;
  private User merchant;
  private String tokenId;
  private int paymentAmount;
  private Response response;

  public PaymentStepDefs(){
    this.bankService = new BankServiceService().getBankServicePort();
  }

  //  -------------------------------------- Tear down --------------------------------------
  /**
   * @author Emilie
   */
  @After("@tagPayment")
  public void after() throws BankServiceException_Exception {
    System.out.println("AFTER: Removing customer and merchant accounts");
    if (customer != null) {
      String customerAccountID = bankService.getAccountByCprNumber(customer.getCprNumber()).getId();
      this.bankService.retireAccount(customerAccountID);
    }

    if (merchant != null) {
      String merchantAccountID = bankService.getAccountByCprNumber(merchant.getCprNumber()).getId();
      this.bankService.retireAccount(merchantAccountID);
    }

    // Remove token from db
    TokenService ts = new TokenService();
    ts.deleteToken(tokenId);
  }

  //  -------------------------------------- Scenario: Succeeding payment --------------------------------------
  /**
   * @author Sarah
   */
  @Given("^a registered customer with the CPR \"([^\"]*)\" has the name is \"([^\"]*)\" \"([^\"]*)\" and a bank account with balance (\\d+)$")
  public void aRegisteredCustomerWithTheCPRHasTheNameIsAndABankAccountWithBalance(String customerCPR, String customerFirstName, String customerLastName, BigDecimal customerInitialBalance) throws BankServiceException_Exception, InterruptedException {
    // Create Customer
    this.customer = new User();
    this.customer.setFirstName(customerFirstName);
    this.customer.setLastName(customerLastName);
    this.customer.setCprNumber(customerCPR);

    // Create Bank account for Customer
    try {
      this.bankService.createAccountWithBalance(customer, customerInitialBalance);
    } catch (BankServiceException_Exception e) {
      String customerAccountID = bankService.getAccountByCprNumber(this.customer.getCprNumber()).getId();
      this.bankService.retireAccount(customerAccountID);

      this.bankService.createAccountWithBalance(customer, customerInitialBalance);
    }

    // Register Customer
    CustomerService customerService = new CustomerService();
    Response response = customerService.registerCustomer(customerFirstName, customerLastName, customerCPR);
    assertEquals(200,response.getStatus());

    System.out.println("Sleeping on this thread; Registering Customer");
    Thread.sleep(1000);
    System.out.println("Slept on this thread; Registering Customer");
  }

  /**
   * @author Emilie
   */
  @And("^a registered merchant with the CVR \"([^\"]*)\" has the name \"([^\"]*)\" \"([^\"]*)\" and a bank account with balance (\\d+)$")
  public void aRegisteredMerchantWithTheCVRHasTheNameAndABankAccountWithBalance(String merchantCVR, String merchantFirstName, String merchantLastName, BigDecimal merchantInitialBalance) throws BankServiceException_Exception {
    // Create merchant
    this.merchant = new User();
    this.merchant.setFirstName(merchantFirstName);
    this.merchant.setLastName(merchantLastName);
    this.merchant.setCprNumber(merchantCVR);

    // Create Bank account for Merchant
    try {
      this.bankService.createAccountWithBalance(merchant, merchantInitialBalance);
    } catch (BankServiceException_Exception e) {
      String merchantAccountId = bankService.getAccountByCprNumber(this.merchant.getCprNumber()).getId();
      this.bankService.retireAccount(merchantAccountId);

      this.bankService.createAccountWithBalance(merchant, merchantInitialBalance);
    }

    // TODO: Register Merchant
  }

    /**
     * A request for a single token is sent to the token service.
     * This request returns a new token and its' ID will be used in the rest of the steps.
     * @author August
     */
  @And("^the customer has a token$")
  public void theCustomerHasAToken() {
      // STEPS
      // - Reqeust new token
      // - Set token ID with response.
      // Request single token to use in following steps
      TokenService tokenService = new TokenService();
      Response res = tokenService.requestTokens(customer.getCprNumber(), 1);
      List<TokenBarcodePair> tokenBarcodePair = res
        .readEntity(TokenGeneratedResponse.class)
        .getTokenBarcodePairs();

      assertEquals("Expected to receive one token/barcode pair", tokenBarcodePair.size(), 1);
      this.tokenId = tokenBarcodePair.get(0).getTokenId();

  }

  /**
   * @author Sarah
   */
  @And("^that the merchant wishes to register a payment of amount (\\d+)$")
  public void thatTheMerchantWishesToRegisterAPaymentOfAmount(int paymentAmount) {
    // STEPS
    // - Save payment amount
    this.paymentAmount = paymentAmount;
  }

  /**
   * @author Sebastian
   */
  @When("^the merchant submits a request for the payment$")
  public void theMerchantSubmitsARequestForThePayment() {
    // STEPS
    // - Submit REST call to server

    PaymentService ps = new PaymentService();
    Response response = ps.submitPayment(merchant.getCprNumber(), this.paymentAmount, this.tokenId);
    this.response = response;
  }

  /**
   * @author Sebastian
   */
  @Then("^the submission succeeds$")
  public void theSubmissionSucceeds() throws InterruptedException {
    // STEPS
    // - Verify status code of Rest response is 200.
    assertEquals(200, this.response.getStatus());

    System.out.println("Sleeping on this thread; waiting for transaction to finish");
    Thread.sleep(10000);
    System.out.println("Slept on this thread; waited for transaction to finish");
  }

  /**
   * @author Sarah
   */
  @And("^after the transaction, the merchant's account has balance (\\d+)$")
  public void afterTheTransactionTheMerchantSAccountHasBalance(BigDecimal merchantBalance) throws BankServiceException_Exception {
    // STEPS
    // - Use SOAP call to bank to retrieve Merchant's account
    // - Verify balance

    System.out.println("Verifying merchant account balance...");
    Account account = this.bankService.getAccountByCprNumber(merchant.getCprNumber());
    assertEquals(merchantBalance, account.getBalance());
    System.out.println("DONE!");
  }

  /**
   * @author Emilie
   */
  @And("^the customer's account has balance (\\d+)$")
  public void theCustomerSAccountHasBalance(BigDecimal customerBalance) throws BankServiceException_Exception, InterruptedException {
    // STEPS
    // - Use SOAP call to bank to retrieve Customer's account
    // - Verify balance

    Account account = this.bankService.getAccountByCprNumber(customer.getCprNumber());
    assertEquals(customerBalance, account.getBalance());
  }

  /**
   * @authour Esben
   */
  @And("^the token has been used$")
  public void theTokenHasBeenUsed() {
    TokenService ts = new TokenService();
    Response response = ts.getTokenById(tokenId);
    //Assert repsponse code to ensure item was found
    assertEquals(200, response.getStatus());
    TokenResponse token = response.readEntity(TokenResponse.class);
    assertTrue(token.isUsed());
  }
  /**
   * @authour August
   */
  @And("^the token is unused$")
  public void theTokenIsUnused() throws Throwable {
    TokenService ts = new TokenService();
    Response response = ts.getTokenById(tokenId);
    //Assert repsponse code to ensure item was found
    assertEquals(200, response.getStatus());
    TokenResponse token = response.readEntity(TokenResponse.class);
    assertFalse(token.isUsed());
  }
    /**
     * @authour August
     */
    @And("^the customer has a used token$")
    public void theCustomerHasAUsedToken() throws InterruptedException {
      // Create and retrieve token
      TokenService tokenService = new TokenService();

      Response res = tokenService.requestTokens(customer.getCprNumber(), 1);
      List<TokenBarcodePair> tokenBarcodePair = res
        .readEntity(TokenGeneratedResponse.class)
        .getTokenBarcodePairs();

      assertEquals("Expected to receive one token/barcode pair", tokenBarcodePair.size(), 1);
      this.tokenId = tokenBarcodePair.get(0).getTokenId();

      // Submit a zero payment to use token
      PaymentService ps = new PaymentService();
      Response response = ps.submitPayment(merchant.getCprNumber(), 0, tokenId); //
      assertEquals(200, response.getStatus());

      System.out.println("Sleeping on this thread; waiting for transaction to finish");
      Thread.sleep(5000);
      System.out.println("Slept on this thread; waited for transaction to finish");

      // Assert token is not used
      Response tokenResponse = tokenService.getTokenById(tokenId);
      assertEquals(200, tokenResponse.getStatus());
      TokenResponse token = tokenResponse.readEntity(TokenResponse.class);
      assertTrue(token.isUsed());
    }
  // --------------------------------------- Refund Scenario ----------------------------------- //

  /**
   * @authour August
   */
  @When("^the merchant submits a request for the refund$")
  public void theMerchantSubmitsARequestForTheRefund() throws InterruptedException {
    // Steps:
    // - Submit refund rest call
    // - Save response

    PaymentService ps = new PaymentService();
    this.response = ps.submitRefund(tokenId);

    System.out.println("Sleeping on this thread; waiting for refund to finish");
    Thread.sleep(10000);
    System.out.println("Slept on this thread; waited for refund to finish");
  }

  /**
   * @author Ebbe (s125015)
   * @throws Throwable
   */

  @And("^the token is identified as already used$")
  public void theTokenIsIdentifiedAsAlreadyUsed() throws Throwable {

    throw new PendingException();
  }

  /**
   * @author Ebbe (s125015)
   * @throws Throwable
   */
  @Then("^the submission fails$")
  public void theSubmissionFails() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }


  /**
   * @author Emilie
   * @param paymentAmount
   */
  @And("^the customer has a used token with a transaction amount of (\\d+)$")
  public void theCustomerHasAUsedTokenWithATransactionAmountOf(int paymentAmount) throws InterruptedException {
    // Steps:
    // - Use token
    // - (Optional) Get token to assert used
    TokenService tokenService = new TokenService();

    Response res = tokenService.requestTokens(customer.getCprNumber(), 1);
    List<TokenBarcodePair> tokenBarcodePair = res
      .readEntity(TokenGeneratedResponse.class)
      .getTokenBarcodePairs();

    assertEquals("Expected to receive one token/barcode pair", tokenBarcodePair.size(), 1);
    this.tokenId = tokenBarcodePair.get(0).getTokenId();
    this.paymentAmount = paymentAmount;

    PaymentService ps = new PaymentService();
    Response response = ps.submitPayment(merchant.getCprNumber(), paymentAmount, tokenId);
    assertEquals(200, response.getStatus());

    System.out.println("Sleeping on this thread; waiting for transaction to finish");
    Thread.sleep(5000);
    System.out.println("Slept on this thread; waited for transaction to finish");



  }

}