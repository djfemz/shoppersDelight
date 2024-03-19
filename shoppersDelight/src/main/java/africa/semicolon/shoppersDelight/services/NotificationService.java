package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.SendNotificationRequest;
import africa.semicolon.shoppersDelight.models.Notification;

public interface NotificationService {
    Notification createNotification(SendNotificationRequest sendNotificationRequest);
}
