package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.CustomerRegistrationRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateCustomerRequest;
import africa.semicolon.shoppersDelight.dtos.response.ApiResponse;
import africa.semicolon.shoppersDelight.dtos.response.CustomerRegistrationResponse;
import africa.semicolon.shoppersDelight.dtos.response.CustomerResponse;
import africa.semicolon.shoppersDelight.dtos.response.UpdateCustomerResponse;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import africa.semicolon.shoppersDelight.models.Cart;
import africa.semicolon.shoppersDelight.models.Customer;
import africa.semicolon.shoppersDelight.models.Notification;

public interface CustomerService extends Notifiable{
    CustomerRegistrationResponse register(CustomerRegistrationRequest request);

    ApiResponse<UpdateCustomerResponse> updateCustomer(Long id, UpdateCustomerRequest request) throws CustomerNotFoundException;

    CustomerResponse getCustomerBy(Long id) throws CustomerNotFoundException;
    Customer getCustomerBy(Cart cart) throws CustomerNotFoundException;

}
