import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import customer.networking.services.CustomerService;
import dtu.ws.fastmoney.*;
import merchant.networking.services.MerchantService;
import payment.networking.services.PaymentService;
import services.CprService;
import services.CvrService;
import token.networking.response.TokenBarcodePair;
import token.networking.response.TokenGeneratedResponse;
import token.networking.response.TokenResponse;
import token.networking.services.TokenService;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

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
  @Given("^a registered customer whose name is \"([^\"]*)\" \"([^\"]*)\" and a bank account with balance (\\d+)$")
  public void aRegisteredCustomerWhoseNameIsAndABankAccountWithBalance(String customerFirstName, String customerLastName, BigDecimal customerInitialBalance) throws Throwable {
    // Create Customer
    this.customer = new User();
    this.customer.setFirstName(customerFirstName);
    this.customer.setLastName(customerLastName);

    // Generate CPR number
    String customerCpr = CprService.generateCpr();
    this.customer.setCprNumber(customerCpr);

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
    Response res = customerService.registerCustomer(customerFirstName, customerLastName, customerCpr);
    assertEquals(200, res.getStatus());

    System.out.println("Sleeping on this thread; Registering Customer");
    Thread.sleep(1000);
    System.out.println("Slept on this thread; Registering Customer");
  }

  /**
   * @author Emilie
   */
  @And("^a registered merchant has a CVR, the name \"([^\"]*)\" \"([^\"]*)\", and a bank account with balance (\\d+)$")
  public void aRegisteredMerchantHasACVRTheNameAndABankAccountWithBalance(String merchantFirstName, String merchantLastName, BigDecimal merchantInitialBalance) throws InterruptedException, BankServiceException_Exception {
    // Create merchant
    this.merchant = new User();
    this.merchant.setFirstName(merchantFirstName);
    this.merchant.setLastName(merchantLastName);

    String merchantCVR = CvrService.generateCvr();
    this.merchant.setCprNumber(merchantCVR);

    // Create Bank account for Merchant
    try {
      this.bankService.createAccountWithBalance(merchant, merchantInitialBalance);
    } catch (BankServiceException_Exception e) {
      String merchantAccountId = bankService.getAccountByCprNumber(this.merchant.getCprNumber()).getId();
      this.bankService.retireAccount(merchantAccountId);

      this.bankService.createAccountWithBalance(merchant, merchantInitialBalance);
    }

    // Register Merchant
    MerchantService ms = new MerchantService();
    Response res = ms.registerMerchant(merchantFirstName, merchantLastName, merchantCVR);

    assertEquals(200, res.getStatus());

    System.out.println("Sleeping on this thread; Registering Merchant");
    Thread.sleep(1000);
    System.out.println("Slept on this thread; Registering Merchant");
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
    // - Use SOAP call to bank to retrieve GetMerchantResponse's account
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


//  -------------------------------------- Scenario:Failing payment because of already used token --------------------------------------

  /**
   * @author Esben
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
   * @author August
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
   * @author August
   */
  @And("^the customer has a used token$")
  public void theCustomerHasAUsedToken() throws InterruptedException {
    // Create and retrieve token
    TokenService tokenService = new TokenService();

    Response res = tokenService.requestTokens(customer.getCprNumber(), 1);
    List<TokenBarcodePair> tokenBarcodePairs = res
      .readEntity(TokenGeneratedResponse.class)
      .getTokenBarcodePairs();

    assertEquals("Expected to receive one token/barcode pair", tokenBarcodePairs.size(), 1);
    this.tokenId = tokenBarcodePairs.get(0).getTokenId();

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

  /**
   * @author Emilie
   */
  @And("^the customer has an unknown token$")
  public void theCustomerHasAnUnknownToken() {
    // Set token ID to a token not known to token service.
    this.tokenId = "Invalid_TokenId";
  }

  /**
   * @author Ebbe
   * @param negativeAmount
   */
  @And("^that the merchant wishes to register a payment of negative amount ([-]?\\d+)$")
  public void   thatTheMerchantWishesToRegisterAPaymentOfNegativeAmount(int negativeAmount) {
    this.paymentAmount = negativeAmount;
  }

  /**
   * @author August
   */
  @Then("^the merchant receives a failure response$")
  public void theMerchantReceivesAFailureResponse() throws Throwable {
    assertEquals(400, response.getStatus());
  }
  // --------------------------------------- Refund Scenario ----------------------------------- //

  /**
   * @author August
   */
  @And("^the customer has a used token with ID \"([^\"]*)\" and an amount of (\\d+)$")
  public void theCustomerHasAUsedTokenWithIDAndAnAmountOf(String tokenId, int paymentAmount) throws InterruptedException {
    // Steps:
    // - TODO: Refac to request token first when database impl is in place
    // - Use token
    // - (Optional) Get token to assert used

    this.tokenId = tokenId;
    this.paymentAmount = paymentAmount;

    // Submit payment to use token and create initial transaction
    PaymentService ps = new PaymentService();
    Response response = ps.submitPayment(merchant.getCprNumber(), paymentAmount, tokenId);
    assertEquals(200, response.getStatus());

    System.out.println("Sleeping on this thread; waiting for transaction to finish");
    Thread.sleep(10000);
    System.out.println("Slept on this thread; waited for transaction to finish");

  }

  /**
   * @author Sebastian
   * @throws InterruptedException
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

  // --------------------------------------- token already used ----------------------------------- //
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
    Response submissionResponse = ps.submitPayment(merchant.getCprNumber(), paymentAmount, tokenId);
    assertEquals(200, submissionResponse.getStatus());

    System.out.println("Sleeping on this thread; waiting for transaction to finish");
    Thread.sleep(5000);
    System.out.println("Slept on this thread; waited for transaction to finish");
  }

  // --------------------------------------- Unregistered Merchant ----------------------------------- //

  /**
   * @author August
   * @param merchantCVR
   * @param merchantFirstName
   * @param merchantLastName
   * @param merchantInitialBalance
   * @throws Throwable
   */
  @And("^a unregistered merchant with the CVR \"([^\"]*)\" has the name \"([^\"]*)\" \"([^\"]*)\" and a bank account with balance (\\d+)$")
  public void aUnregisteredMerchantWithTheCVRHasTheNameAndABankAccountWithBalance(String merchantCVR, String merchantFirstName, String merchantLastName, BigDecimal merchantInitialBalance) throws Throwable {
    // Create merchant
    this.merchant = new User();
    this.merchant.setFirstName(merchantFirstName);
    this.merchant.setLastName(merchantLastName);
    this.merchant.setCprNumber(merchantCVR);

    // Create Bank account for Mechant
    try {
      this.bankService.createAccountWithBalance(merchant, merchantInitialBalance);
    } catch (BankServiceException_Exception e) {
      String merchantAccountId = bankService.getAccountByCprNumber(this.merchant.getCprNumber()).getId();
      this.bankService.retireAccount(merchantAccountId);

      this.bankService.createAccountWithBalance(merchant, merchantInitialBalance);
    }

    // Will not be registered
  }

  /**
   * @author August
   * @param merchantFirstName
   * @param merchantLastName
   * @param merchantInitialBalance
   * @throws Throwable
   */
  @And("^a unregistered merchant has a CVR, the name \"([^\"]*)\" \"([^\"]*)\", and a bank account with balance (\\d+)$")
  public void aUnregisteredMerchantHasACVRTheNameAndABankAccountWithBalance(String merchantFirstName, String merchantLastName, BigDecimal merchantInitialBalance) throws Throwable {
    // Create merchant
    this.merchant = new User();
    this.merchant.setFirstName(merchantFirstName);
    this.merchant.setLastName(merchantLastName);

    String merchantCVR = CvrService.generateCvr();
    this.merchant.setCprNumber(merchantCVR);

    // Create Bank account for Mechant
    try {
      this.bankService.createAccountWithBalance(merchant, merchantInitialBalance);
    } catch (BankServiceException_Exception e) {
      String merchantAccountId = bankService.getAccountByCprNumber(this.merchant.getCprNumber()).getId();
      this.bankService.retireAccount(merchantAccountId);

      this.bankService.createAccountWithBalance(merchant, merchantInitialBalance);
    }

    // Will not be registered
  }
}