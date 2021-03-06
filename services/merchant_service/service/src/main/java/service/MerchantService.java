package service;

import data.IDataSource;
import exceptions.*;
import management.IMerchantManagement;
import domain.CVRNumber;
import domain.Merchant;
import exceptions.InvalidCvrException;
import exceptions.MerchantServiceException;
import networking.adapters.message_queue.domain.MerchantInfoVerified;
import networking.adapters.message_queue.domain.PaymentInitializedRequest;
import networking.adapters.message_queue.notification.INotification;
import networking.adapters.rest.requests.RegisterMerchantRequest;
import networking.adapters.rest.responses.GetMerchantResponse;

public class MerchantService {

    private final IMerchantManagement merchantManagement;
    private IDataSource data;
    private INotification iNotification;


    /**
     * @author Sarah (s153659)
     * @param data
     * @param iNotification
     */
    public MerchantService(IDataSource data, IMerchantManagement merchantManagement, INotification iNotification) {
        this.data = data;
        this.merchantManagement = merchantManagement;
        this.iNotification = iNotification;
    }

    /**
     * This method verifies the merchant, when receiving the request and then it publishes the verification.
     * @author Emilie (s153762)
     * @param paymentInitializedRequest
     */
    public void handlePaymentInitialized(PaymentInitializedRequest paymentInitializedRequest) {
        String merchantCVR = paymentInitializedRequest.getMerchantId();
        try {
            data.getMerchant(new CVRNumber(merchantCVR));
            MerchantInfoVerified merchantInfoVerified =
                    new MerchantInfoVerified(
                            paymentInitializedRequest.getMerchantId(),
                            paymentInitializedRequest.getPaymentAmount(),
                            paymentInitializedRequest.getTokenId(),
                            paymentInitializedRequest.getCprNumber());

            iNotification.publishMessage(merchantInfoVerified);
        } catch (MerchantNotFoundException | MessagePublishException | InvalidCvrException e) {
           e.printStackTrace();
        }


    }

    /**
     * This method calls merchant management and registers the merchant.
     * @author Sarah (s153659)
     * @param req
     * @throws MerchantRegistrationException
     */
    public void registerMerchant(RegisterMerchantRequest req) throws MerchantRegistrationException {
        try{
            merchantManagement.registerMerchant(req.getFirstName(), req.getLastName(), new CVRNumber(req.getCvr()));
        } catch (MerchantInvalidInformation | MerchantInvalidName | MerchantAlreadyExistException | InvalidCvrException e) {
            throw new MerchantRegistrationException(e.getMessage());
        }
    }

    /**
     * This method calls merchant management and gets the merchant with the given cvr.
     * @author Sarah (s153659)
     * @param cvr
     * @return
     * @throws MerchantServiceException
     */
    public GetMerchantResponse getMerchant(String cvr) throws MerchantServiceException {
        try {
            System.out.println(cvr);
            Merchant merchant = merchantManagement.getMerchant(new CVRNumber(cvr));
            return new GetMerchantResponse(merchant.getFirstName(), merchant.getLastName(), merchant.getCvr().getCvrNumber());
        } catch (MerchantNotFoundException | InvalidCvrException e) {
            throw new MerchantServiceException(e.getMessage());
        }
    }
}
