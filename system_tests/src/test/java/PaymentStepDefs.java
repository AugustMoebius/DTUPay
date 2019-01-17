import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import payment.networking.services.PaymentService;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class PaymentStepDefs {
  private String tokenId;
  private String merchantId;
  private int paymentAmount;

  private Response response;

  @Given("^that the merchant has a barcode containing token ID \"([^\"]*)\"$")
  public void thatTheMerchantHasABarcodeContainingTokenID(String tokenId) {
    // STEPS
    // - Save token ID
    this.tokenId = tokenId;
  }

  @And("^that the merchant ID is \"([^\"]*)\"$")
  public void thatTheMerchantIDIs(String merchantId) {
    // STEPS
    // - Save Merchant ID

    this.merchantId = merchantId;
  }

  @And("^that the merchant wishes to register a payment of amount (\\d+)$")
  public void thatTheMerchantWishesToRegisterAPaymentOfAmount(int paymentAmount) {
    // STEPS
    // - Save payment amount

    this.paymentAmount = paymentAmount;
  }

  @When("^the merchant submits a request for the payment$")
  public void theMerchantSubmitsARequestForThePayment() {
    // STEPS
    // - Submit REST call to server

    PaymentService ps = new PaymentService();
    Response response = ps.submitPayment(this.merchantId, this.paymentAmount, this.tokenId);

    this.response = response;
  }

  @Then("^the payment request succeeds$")
  public void thePaymentRequestSucceeds() {
    // STEPS
    // - Verify status code of Rest response is 200.

    assertEquals(200, this.response.getStatus());
  }
}
