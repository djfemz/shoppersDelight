package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.CustomerNotificationRequest;
import africa.semicolon.shoppersDelight.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateCustomerRequest;
import africa.semicolon.shoppersDelight.dtos.response.ApiResponse;
import africa.semicolon.shoppersDelight.dtos.response.CustomerRegistrationResponse;
import africa.semicolon.shoppersDelight.dtos.response.NotificationResponse;
import africa.semicolon.shoppersDelight.dtos.response.UpdateCustomerResponse;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import africa.semicolon.shoppersDelight.models.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private NotificationService notificationService;
    @Test
    public void notifyCustomerAfterUpdateTest() throws CustomerNotFoundException {

        CustomerNotificationRequest notificationRequest = new CustomerNotificationRequest();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setEmail("qudua55@gmail.com");
        customer.setPassword("niestajnr1");

        notificationRequest.setId(customer.getId());
        notificationRequest.setCustomer(customer);
        notificationRequest.setMessage("updated .......");
        NotificationResponse notificationResponse = notificationService.send(notificationRequest);

        assertNotNull(notificationResponse);
    }

}
