import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.ws.fastmoney.*;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class PaymentStepDefs {
  private final BankService bankService;
  private User customer;
  private String tokenId;
  private User merchant;
  // private Customer customer...
  // private token id
  // private Merchant merchant...

  public PaymentStepDefs(){
    this.bankService = new BankServiceService().getBankServicePort();
  }

  @After("@tagPayment")
  public void after() throws BankServiceException_Exception {
    String customerAccountID = bankService.getAccountByCprNumber(customer.getCprNumber()).getId();
    this.bankService.retireAccount(customerAccountID);

    String merchantAccountID = bankService.getAccountByCprNumber(merchant.getCprNumber()).getId();
    this.bankService.retireAccount(merchantAccountID);
  }

  @Given("^a registered customer with the CPR number \"([^\"]*)\"$")
  public void aRegisteredCustomerWithTheCPRNumber(String customerCPR) throws Throwable {
    // STEPS
    // - Create dummy Customer (`new Customer()`) with CPR number
    this.customer = new User();
    this.customer.setCprNumber(customerCPR);
  }

  @And("^the customer's name is \"([^\"]*)\" \"([^\"]*)\"$")
  public void theCustomerSNameIs(String customerFirstName, String customerLastName) throws Throwable {
    // STEPS
    // - Add name to customer
    this.customer.setFirstName(customerFirstName);
    this.customer.setLastName(customerLastName);
  }

  @And("^the customer has a bank account with balance (\\d+)$")
  public void theCustomerHasABankAccountWithBalance(BigDecimal customerInitialBalance) throws BankServiceException_Exception {
    // STEPS
    // - Create account in Bank
    this.bankService.createAccountWithBalance(customer, customerInitialBalance);
  }

  @And("^the customer has a token with ID \"([^\"]*)\"$")
  public void theCustomerHasATokenWithID(String tokenId) throws Throwable {
    // STEPS
    // - Store ID to this instance
    this.tokenId = tokenId;
  }

  @And("^a registered merchant with the CVR \"([^\"]*)\" has the name \"([^\"]*)\" \"([^\"]*)\" and a bank account with balance (\\d+)$")
  public void aRegisteredMerchantWithTheCVRHasTheNameAndABankAccountWithBalance(String merchantCVR, String merchantFirstName, String merchantLastName, BigDecimal merchantInitialBalance) throws BankServiceException_Exception {
    // STEPS
    // - Create dummy Merchant
    // - Create account for merchant

    this.merchant = new User();
    this.merchant.setFirstName(merchantFirstName);
    this.merchant.setLastName(merchantLastName);
    this.merchant.setCprNumber(merchantCVR);

    this.bankService.createAccountWithBalance(merchant,merchantInitialBalance);
  }

  @When("^the merchant scans the token with a request for a payment of (\\d+) kroner$")
  public void theMerchantScansTheTokenWithARequestForAPaymentOfKroner(int arg0) {
    // STEPS
    // - Send request for payment (client -> server; REST)
  }

  @Then("^the payment request succeeds$")
  public void thePaymentSucceeds() {
    // STEPS
    // - Verify status code in [200; 300[
  }

  @And("^after the transaction, the merchant's account has balance (\\d+)$")
  public void afterTheTransactionTheMerchantSAccountHasBalance(BigDecimal merchantBalance) throws BankServiceException_Exception {
    // STEPS
    // - Use SOAP call to bank to retrieve Merchant's account
    // - Verify balance
    throw new PendingException();
    //Account account = this.bankService.getAccountByCprNumber(merchant.getCprNumber());
    //assertEquals(merchantBalance,account.getBalance());

  }

  @And("^the customer's account has balance (\\d+)$")
  public void theCustomerSAccountHasBalance(BigDecimal customerBalance) throws BankServiceException_Exception {
    // STEPS
    // - Use SOAP call to bank to retrieve Customer's account
    // - Verify balance
    throw new PendingException();
    //Account account = this.bankService.getAccountByCprNumber(customer.getCprNumber());
    //assertEquals(customerBalance,account.getBalance());
  }

}
