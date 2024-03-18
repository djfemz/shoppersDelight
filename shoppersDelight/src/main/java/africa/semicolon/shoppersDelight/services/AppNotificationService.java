package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.CustomerNotificationRequest;
import africa.semicolon.shoppersDelight.dtos.response.NotificationResponse;
import africa.semicolon.shoppersDelight.models.Customer;
import africa.semicolon.shoppersDelight.models.NotificationShoppers;
import africa.semicolon.shoppersDelight.models.UserRoles;
import africa.semicolon.shoppersDelight.repositories.CustomerRepository;
import africa.semicolon.shoppersDelight.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppNotificationService implements  NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private CustomerService customerService;
    @Override
    public NotificationResponse send(CustomerNotificationRequest customerNotificationRequest) {
        NotificationShoppers notificationShoppers = new NotificationShoppers();
        notificationShoppers.setMessage(customerNotificationRequest.getMessage());
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setMessage(notificationShoppers.getMessage());
        notificationRepository.save(notificationShoppers);
        customerService.addNotification(customerNotificationRequest.getId(),notificationShoppers);
        return notificationResponse;
    }
}
