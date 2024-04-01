package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.SendNotificationRequest;
import africa.semicolon.shoppersDelight.models.Notification;
import africa.semicolon.shoppersDelight.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShoppersDelightNotificationService implements  NotificationService{
    private final NotificationRepository notificationRepository;
    @Override
    public Notification createNotification(SendNotificationRequest sendNotificationRequest) {
        Notification notification = new Notification();
        notification.setMessage(sendNotificationRequest.getMessage());
        return notificationRepository.save(notification);
    }
}
