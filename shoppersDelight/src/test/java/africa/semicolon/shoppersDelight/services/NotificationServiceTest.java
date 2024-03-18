package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.CustomerNotificationRequest;
import africa.semicolon.shoppersDelight.dtos.response.NotificationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;
    @Test
    public void notifyCustomerAfterUpdateTest()  {

        CustomerNotificationRequest notificationRequest = new CustomerNotificationRequest();
        notificationRequest.setId(1L);
        notificationRequest.setMessage("notify .......");
        NotificationResponse notificationResponse = notificationService.send(notificationRequest);
        assertNotNull(notificationResponse);
    }

}
