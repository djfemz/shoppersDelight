package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.SendNotificationRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateCustomerRequest;
import africa.semicolon.shoppersDelight.dtos.response.NotificationResponse;
import africa.semicolon.shoppersDelight.dtos.response.UpdateCustomerResponse;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import africa.semicolon.shoppersDelight.models.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private CustomerService customerService;
    @Test
    @Sql({"/scripts/insert.sql"})
    public void notifyCustomerAfterUpdateTest() throws CustomerNotFoundException {
        var notificationCount = customerService.getCustomerBy(100L).getNotifications().size();
        UpdateCustomerRequest request = new UpdateCustomerRequest();
        request.setEmail("john@email.com");
        customerService.updateCustomer(100L, request);
        var currentNotificationCount = customerService.getCustomerBy(100L).getNotifications().size();
        assertThat(currentNotificationCount).isGreaterThan(notificationCount);
    }

}
