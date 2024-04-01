package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.CreateOrderRequest;
import africa.semicolon.shoppersDelight.dtos.response.CreateOrderResponse;
import africa.semicolon.shoppersDelight.dtos.response.OrderResponse;
import africa.semicolon.shoppersDelight.exceptions.CartNotFoundException;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import africa.semicolon.shoppersDelight.models.CustomerOrder;

public interface OrderService {
    CreateOrderResponse create(CreateOrderRequest request) throws CartNotFoundException, CustomerNotFoundException;

    CustomerOrder findBy(Long id);
}
