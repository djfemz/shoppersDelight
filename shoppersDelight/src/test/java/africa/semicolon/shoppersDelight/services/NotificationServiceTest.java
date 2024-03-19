package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.SendNotificationRequest;
import africa.semicolon.shoppersDelight.dtos.response.NotificationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CustomerService customerService;
    @Test
    public void notifyCustomerAfterUpdateTest()  {

        SendNotificationRequest notificationRequest = new SendNotificationRequest();
        notificationRequest.setCustomerId(1L);
        notificationRequest.setMessage("notify .......");
        NotificationResponse notificationResponse = notificationService.createNotification(notificationRequest);
        assertNotNull(notificationResponse);
    }

}
