package service;

import data.IDataSource;
import networking.notifications.INotificationService;

public class PaymentService {
    private IDataSource dataSource;
    private INotificationService notificationService;

    public PaymentService(IDataSource dataSource, INotificationService notificationService) {
        this.dataSource = dataSource;
        this.notificationService = notificationService;
    }
}