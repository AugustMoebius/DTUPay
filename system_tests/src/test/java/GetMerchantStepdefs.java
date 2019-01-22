import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import merchant.networking.responses.GetMerchantResponse;
import merchant.networking.services.MerchantService;


import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetMerchantStepdefs {

    private final RegisterMerchantStepDefs registerMerchantStepDefs;
    private final MerchantService merchantService;
    private String firstName, lastName, cvrNumber;
    private Response res;

    public GetMerchantStepdefs() {
        this.registerMerchantStepDefs = new RegisterMerchantStepDefs();
        this.merchantService = new MerchantService();
    }

    @Given("^a registered merchant with the CVR \"([^\"]*)\" and has the name \"([^\"]*)\" \"([^\"]*)\" and a bank account with balance (\\d+)$")
    public void aRegisteredMerchantWithTheCVRAndHasTheNameAndABankAccountWithBalance(String firstName, String lastName, String cvrNumber, int merchantBalance) throws Throwable {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cvrNumber = cvrNumber;
        registerMerchantStepDefs.aMerchantWithTheCVRNumberWithTheName(firstName, lastName, cvrNumber);
        registerMerchantStepDefs.theMerchantHasAnAccountInTheDTUBankWithABalanceAt(merchantBalance);
        registerMerchantStepDefs.theMerchantSubmitsARequestToRegister();
    }

    @When("^the merchant submits a request to get the user information$")
    public void theMerchantSubmitsARequestToGetTheUserInformation() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // Sends get request to get the merchant
        this.res = merchantService.getMerchant(cvrNumber);
        //throw new PendingException();
    }

    @Then("^the get submission succeeds and the merchant gets the information$")
    public void theGetSubmissionSucceedsAndTheMerchantGetsTheInformation() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(200 <= res.getStatus() && res.getStatus() < 300);
        GetMerchantResponse merchantResponse = res.readEntity(GetMerchantResponse.class);
        assertEquals(firstName, merchantResponse.getFirstName());
        assertEquals(lastName, merchantResponse.getLastName());
        assertEquals(cvrNumber, merchantResponse.getCvr());

        throw new PendingException();
    }

}
