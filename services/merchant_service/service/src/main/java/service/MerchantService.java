package service;

import data.IDataSource;
import exceptions.MerchantInvalidInformation;
import exceptions.MerchantInvalidName;
import exceptions.MerchantNotFoundException;
import exceptions.MessagePublishException;
import management.IMerchantManagement;
import management.domain.CVRNumber;
import management.domain.Merchant;
import management.exceptions.InvalidCvrException;
import networking.adapters.message_queue.domain.MerchantInfoVerified;
import networking.adapters.message_queue.domain.PaymentInitializedRequest;
import networking.adapters.message_queue.notification.INotification;
import networking.adapters.rest.requests.GetMerchantRequest;
import networking.adapters.rest.requests.RegisterMerchantRequest;

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
        boolean isVerified = false;
        try {
            data.getMerchant(new CVRNumber(merchantCVR));
            isVerified = true;
        } catch (MerchantNotFoundException | InvalidCvrException e) {
            e.printStackTrace();
        }

        MerchantInfoVerified merchantInfoVerified =
                new MerchantInfoVerified(
                        paymentInitializedRequest.getMerchantId(),
                        paymentInitializedRequest.getPaymentAmount(),
                        paymentInitializedRequest.getTokenId(),
                        paymentInitializedRequest.getCustomerId());

        try {
            iNotification.publishMessage(merchantInfoVerified, isVerified);
        } catch (MessagePublishException e) {
            e.printStackTrace();
        }


    }

    public void registerMerchant(RegisterMerchantRequest req) {
        try{
            merchantManagement.registerMerchant(req.getFirstName(), req.getLastName(), new CVRNumber(req.getCvr()));
        } catch (MerchantInvalidInformation | MerchantInvalidName | InvalidCvrException e) {
            e.printStackTrace();
        }
    }

    public Merchant getMerchant(String cvr) {
        try {
            return merchantManagement.getMerchant(new CVRNumber(cvr));
        } catch (MerchantNotFoundException | InvalidCvrException e) {
            e.printStackTrace();
        }
        return null;
    }
}
