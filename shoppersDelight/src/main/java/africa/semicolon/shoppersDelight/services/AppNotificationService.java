package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.SendNotificationRequest;
import africa.semicolon.shoppersDelight.dtos.response.NotificationResponse;
import africa.semicolon.shoppersDelight.models.Notification;
import africa.semicolon.shoppersDelight.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppNotificationService implements  NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private CustomerService customerService;
    @Override
    public Notification createNotification(SendNotificationRequest sendNotificationRequest) {
        Notification notification = new Notification();
        notification.setMessage(sendNotificationRequest.getMessage());
        return notificationRepository.save(notification);
    }
}
