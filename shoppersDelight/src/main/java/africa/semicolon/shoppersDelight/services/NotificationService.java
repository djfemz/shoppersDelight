package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.CustomerNotificationRequest;
import africa.semicolon.shoppersDelight.dtos.response.NotificationResponse;

public interface NotificationService {
    NotificationResponse send(CustomerNotificationRequest customerNotificationRequest);
}
