import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.ws.fastmoney.*;
import payment.networking.services.PaymentService;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

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
    if(customer!=null) {
      String customerAccountID = bankService.getAccountByCprNumber(customer.getCprNumber()).getId();
      this.bankService.retireAccount(customerAccountID);

      String merchantAccountID = bankService.getAccountByCprNumber(merchant.getCprNumber()).getId();
      this.bankService.retireAccount(merchantAccountID);
    }
  }

  //  -------------------------------------- Scenario: Succeeding payment --------------------------------------
  /**
   * @author Sarah
   */
  @Given("^a registered customer with the CPR \"([^\"]*)\" has the name is \"([^\"]*)\" \"([^\"]*)\" and a bank account with balance (\\d+)$")
  public void aRegisteredCustomerWithTheCPRHasTheNameIsAndABankAccountWithBalance(String customerCPR, String customerFirstName, String customerLastName, BigDecimal customerInitialBalance) throws BankServiceException_Exception {
    this.customer = new User();
    this.customer.setFirstName(customerFirstName);
    this.customer.setLastName(customerLastName);
    this.customer.setCprNumber(customerCPR);

    try {
      this.bankService.createAccountWithBalance(customer, customerInitialBalance);
    } catch (BankServiceException_Exception e) {
      String customerAccountID = bankService.getAccountByCprNumber(this.customer.getCprNumber()).getId();
      this.bankService.retireAccount(customerAccountID);

      this.bankService.createAccountWithBalance(customer, customerInitialBalance);
    }
  }

  /**
   * @author Sarah
   */
  @And("^the customer has a token with ID \"([^\"]*)\"$")
  public void theCustomerHasATokenWithID(String tokenId) {
    // STEPS
    // - Store ID to this instance
    this.tokenId = tokenId;
  }

  /**
   * @author Emilie
   */
  @And("^a registered merchant with the CVR \"([^\"]*)\" has the name \"([^\"]*)\" \"([^\"]*)\" and a bank account with balance (\\d+)$")
  public void aRegisteredMerchantWithTheCVRHasTheNameAndABankAccountWithBalance(String merchantCVR, String merchantFirstName, String merchantLastName, BigDecimal merchantInitialBalance) throws BankServiceException_Exception {
    this.merchant = new User();
    this.merchant.setFirstName(merchantFirstName);
    this.merchant.setLastName(merchantLastName);
    this.merchant.setCprNumber(merchantCVR);

    try {
      this.bankService.createAccountWithBalance(merchant, merchantInitialBalance);
    } catch (BankServiceException_Exception e) {
      String merchantAccountId = bankService.getAccountByCprNumber(this.merchant.getCprNumber()).getId();
      this.bankService.retireAccount(merchantAccountId);

      this.bankService.createAccountWithBalance(merchant, merchantInitialBalance);
    }

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
  @Then("^the payment submission succeeds$")
  public void thePaymentSubmissionSucceeds() {
    // STEPS
    // - Verify status code of Rest response is 200.
    assertEquals(200, this.response.getStatus());
  }

  /**
   * @author Sarah
   */
  @And("^after the transaction, the merchant's account has balance (\\d+)$")
  public void afterTheTransactionTheMerchantSAccountHasBalance(BigDecimal merchantBalance) throws BankServiceException_Exception, InterruptedException {
    // STEPS
    // - Use SOAP call to bank to retrieve Merchant's account
    // - Verify balance
    System.out.println("Sleeping on this thread");
    Thread.sleep(10000);
    System.out.println("Slept on this thread");

    // Thread sleep to wait for bank call to complete
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
    System.out.println("Sleeping on this thread");
    Thread.sleep(10000);
    System.out.println("Slept on this thread");

    Account account = this.bankService.getAccountByCprNumber(customer.getCprNumber());
    assertEquals(customerBalance, account.getBalance());
  }

}