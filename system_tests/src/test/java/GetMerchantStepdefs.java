import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;
import dtu.ws.fastmoney.User;
import gherkin.deps.com.google.gson.Gson;
import merchant.networking.responses.GetMerchantResponse;
import merchant.networking.services.MerchantService;


import javax.ws.rs.core.Response;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetMerchantStepdefs {

    private final MerchantService merchantService;
    private Response res;
    private User merchant;
    private BankService bankService;

    public GetMerchantStepdefs() {
        this.merchantService = new MerchantService();
        this.bankService = new BankServiceService().getBankServicePort();
    }

    /**
     * @author Sarah
     * @param firstName
     * @param lastName
     * @param cvrNumber
     * @param merchantBalance
     * @throws Throwable
     */
    @Given("^a registered merchant with the CVR \"([^\"]*)\" and has the name \"([^\"]*)\" \"([^\"]*)\" and a bank account with balance (\\d+)$")
    public void aRegisteredMerchantWithTheCVRAndHasTheNameAndABankAccountWithBalance(String cvrNumber, String firstName, String lastName, int merchantBalance) throws Throwable {
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

        MerchantService ms = new MerchantService();
        this.res = ms.registerMerchant(merchant.getFirstName(), merchant.getLastName(), merchant.getCprNumber());
    }

    /**
     * @author Sarah
     * @throws Throwable
     */
    @When("^the merchant submits a request to get the user information$")
    public void theMerchantSubmitsARequestToGetTheUserInformation() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // Sends get request to get the merchant
        this.res = merchantService.getMerchant(merchant.getCprNumber());
        //throw new PendingException();
    }

    /**
     * @author Sarah
     * @throws Throwable
     */
    @Then("^the get submission succeeds and the merchant gets the information$")
    public void theGetSubmissionSucceedsAndTheMerchantGetsTheInformation() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(res.getStatus());
        assertTrue(200 <= res.getStatus());
        assertTrue(res.getStatus() < 300);

        String getMerchantResponseJson = res.readEntity(String.class);
        Gson gson = new Gson();
        GetMerchantResponse merchantResponse = gson.fromJson(getMerchantResponseJson, GetMerchantResponse.class);
        //GetMerchantResponse merchantResponse = res.readEntity(GetMerchantResponse.class);


        assertEquals(merchant.getFirstName(), merchantResponse.getFirstName());
        assertEquals(merchant.getLastName(), merchantResponse.getLastName());
        assertEquals(merchant.getCprNumber(), merchantResponse.getCvr());

        //throw new PendingException();
    }

   /* @Given("^a unregistered merchant with the CVR \"([^\"]*)\" with the name \"([^\"]*)\" \"([^\"]*)\"$")
    public void aUnregisteredMerchantWithTheCVRWithTheName(String cvrNumber, String firstName, String lastName) throws Throwable {
        this.merchant = new User();
        this.merchant.setFirstName(firstName);
        this.merchant.setLastName(lastName);
        this.merchant.setCprNumber(cvrNumber);
    }*/
}
