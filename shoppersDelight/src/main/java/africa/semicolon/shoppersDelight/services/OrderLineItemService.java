package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.response.OrderLineItemResponse;
import africa.semicolon.shoppersDelight.models.CustomerOrder;
import africa.semicolon.shoppersDelight.models.OrderLineItem;

import java.util.List;

public interface OrderLineItemService {
    List<OrderLineItem> create(List<Long> items, CustomerOrder customerOrder);

    OrderLineItemResponse getBy(Long id);
}
