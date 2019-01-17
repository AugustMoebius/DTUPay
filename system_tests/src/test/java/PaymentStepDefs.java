import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PaymentStepDefs {
  // private Customer customer...
  // private token id
  // private Merchant merchant...

  @Given("^a registered customer with the CPR number \"([^\"]*)\"$")
  public void aRegisteredCustomerWithTheCPRNumber(String arg0) throws Throwable {
    // STEPS
    // - Create dummy Customer (`new Customer()`) with CPR number


    throw new PendingException();
  }

  @And("^the customer's name is \"([^\"]*)\"$")
  public void theCustomerSNameIs(String arg0) throws Throwable {
    // STEPS
    // - Add name to customer


    throw new PendingException();
  }

  @And("^the customer has a bank account with balance (\\d+)$")
  public void theCustomerHasABankAccountWithBalance(int arg0) {
    // STEPS
    // - Create account in Bank
  }

  @And("^the customer has a token with ID \"([^\"]*)\"$")
  public void theCustomerHasATokenWithID(String arg0) throws Throwable {
    // STEPS
    // - Store ID to this instance

    throw new PendingException();
  }

  @And("^a registered merchant with the CVR \"([^\"]*)\" has the name \"([^\"]*)\" and a bank account with balance (\\d+)$")
  public void aRegisteredMerchantWithTheCVRHasTheNameAndABankAccountWithBalance(String arg0, String arg1, int arg2) throws Throwable {
    // STEPS
    // - Create dummy Merchant
    // - Create account for merchant

    throw new PendingException();
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

  @And("^the money is transferred from the customer's bank account to the merchant's bank account$")
  public void theMoneyIsTransferredFromTheCustomerSBankAccountToTheMerchantSBankAccount() {
    // STEPS
    // -
  }

  @And("^after the transaction, the merchant's account has balance (\\d+)$")
  public void afterTheTransactionTheMerchantSAccountHasBalance(int arg0) {
    // STEPS
    // - Use SOAP call to bank to retrieve Merchant's account
    // - Verify balance
  }

  @And("^the customer's account has balance (\\d+)$")
  public void theCustomerSAccountHasBalance(int arg0) {
    // STEPS
    // - Use SOAP call to bank to retrieve Customer's account
    // - Verify balance
  }
}
