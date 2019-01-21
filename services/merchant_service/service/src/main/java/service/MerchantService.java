package service;

import data.IDataSource;
import exceptions.MerchantNotFoundException;
import exceptions.MessagePublishException;
import management.IMerchantManagement;
import networking.adapters.message_queue.domain.MerchantInfoVerified;
import networking.adapters.message_queue.domain.PaymentInitializedRequest;
import networking.adapters.message_queue.notification.INotification;
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
            data.getMerchant(merchantCVR);
            isVerified = true;
        } catch (MerchantNotFoundException e) {
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
    }
}
