package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.response.ItemResponse;
import africa.semicolon.shoppersDelight.dtos.response.OrderLineItemResponse;
import africa.semicolon.shoppersDelight.models.CustomerOrder;
import africa.semicolon.shoppersDelight.models.OrderLineItem;
import africa.semicolon.shoppersDelight.repositories.OrderLineItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;


@Service
@AllArgsConstructor
public class ShoppersDelightOrderLineItemService implements OrderLineItemService{
   private final OrderLineItemRepository orderLineItemRepository;
   private final ItemService itemService;

    @Override
    public List<OrderLineItem> create(List<Long> items, CustomerOrder customerOrder) {
        List<ItemResponse> foundItems = itemService.getAllItems(items);
        customerOrder.setAmount(getTotalAmountFor(foundItems).toPlainString());
        List<OrderLineItem> orderedItems = foundItems.stream()
                .map(itemResponse -> new OrderLineItem(itemResponse, customerOrder))
                .toList();
        return orderLineItemRepository.saveAll(orderedItems);
    }

    @Override
    public OrderLineItemResponse getBy(Long id) {
        return null;
    }



    private static BigDecimal getTotalAmountFor(List<ItemResponse> items) {
        return items.stream()
                .map(itemResponse -> itemResponse.getPrice()
                        .multiply(valueOf(itemResponse.getQuantity())))
                .reduce(BigDecimal::add).orElse(ZERO);
    }

}
