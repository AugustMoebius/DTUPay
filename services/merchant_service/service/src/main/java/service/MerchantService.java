package service;

import data.IDataSource;
import exceptions.MerchantNotFoundException;
import exceptions.MessagePublishException;
import networking.adapters.message_queue.domain.MerchantInfoVerified;
import networking.adapters.message_queue.domain.PaymentInitializedRequest;
import networking.adapters.message_queue.notification.INotification;

public class MerchantService {

    private IDataSource data;
    private INotification iNotification;


    /**
     * @author Sarah
     * @param data
     * @param iNotification
     */
    public MerchantService(IDataSource data, INotification iNotification) {
        this.data = data;
        this.iNotification = iNotification;
    }

    /**
     * @author Emilie
     * @param paymentInitializedRequest
     */
    public void handlePaymentInitialized(PaymentInitializedRequest paymentInitializedRequest) {
        String merchantCVR = paymentInitializedRequest.getMerchantId();
        try {
            data.getMerchant(merchantCVR);
            MerchantInfoVerified merchantInfoVerified =
                    new MerchantInfoVerified(
                            paymentInitializedRequest.getMerchantId(),
                            paymentInitializedRequest.getPaymentAmount(),
                            paymentInitializedRequest.getTokenId(),
                            paymentInitializedRequest.getCustomerId());

            iNotification.publishMessage(merchantInfoVerified);
        } catch (MerchantNotFoundException | MessagePublishException e) {
           e.printStackTrace();
        }


    }
}
