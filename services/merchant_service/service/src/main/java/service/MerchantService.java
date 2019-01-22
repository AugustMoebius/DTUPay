package service;

import data.IDataSource;
import exceptions.*;
import management.IMerchantManagement;
import management.domain.CVRNumber;
import management.domain.Merchant;
import management.exceptions.InvalidCvrException;
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
     * @author Sarah
     * @param data
     * @param iNotification
     */
    public MerchantService(IDataSource data, IMerchantManagement merchantManagement, INotification iNotification) {
        this.data = data;
        this.merchantManagement = merchantManagement;
        this.iNotification = iNotification;
    }

    /**
     * @author Emilie
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

    public void registerMerchant(RegisterMerchantRequest req) throws MerchantRegistrationException {
        try{
            merchantManagement.registerMerchant(req.getFirstName(), req.getLastName(), new CVRNumber(req.getCvr()));
        } catch (MerchantInvalidInformation | MerchantInvalidName | MerchantAlreadyExistException | InvalidCvrException e) {
            throw new MerchantRegistrationException(e.getMessage());
        }
    }

    public GetMerchantResponse getMerchant(String cvr) {
        try {
            Merchant merchant = merchantManagement.getMerchant(new CVRNumber(cvr));
            return new GetMerchantResponse(merchant.getFirstName(), merchant.getLastName(), merchant.getCvr().getCvrNumber());
        } catch (MerchantNotFoundException | InvalidCvrException e) {
            e.printStackTrace();
        }
        return null;
    }
}
