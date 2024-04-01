package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.CreateOrderRequest;
import africa.semicolon.shoppersDelight.dtos.response.CreateOrderResponse;
import africa.semicolon.shoppersDelight.dtos.response.OrderResponse;
import africa.semicolon.shoppersDelight.exceptions.CartNotFoundException;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import africa.semicolon.shoppersDelight.exceptions.OrderNotFoundException;
import africa.semicolon.shoppersDelight.models.Cart;
import africa.semicolon.shoppersDelight.models.CustomerOrder;
import africa.semicolon.shoppersDelight.models.OrderLineItem;
import africa.semicolon.shoppersDelight.repositories.CustomerOrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.math.BigDecimal.valueOf;

@Service
@AllArgsConstructor
public class ShoppersDelightOrderService implements OrderService {

    private final ModelMapper modelMapper;
    private final CartService cartService;
    private final CustomerService customerService;
    private final OrderLineItemService orderLineItemService;
    private final CustomerOrderRepository customerOrderRepository;


    @Override
    public CreateOrderResponse create(CreateOrderRequest request) throws CartNotFoundException, CustomerNotFoundException {
        CustomerOrder customerOrder = modelMapper.map(request, CustomerOrder.class);
        Cart cart = cartService.getCartBy(request.getCartId());
        customerOrder.setCustomer(customerService.getCustomerBy(cart));
        customerOrder=customerOrderRepository.save(customerOrder);
        var items = orderLineItemService.create(request.getItems(), customerOrder);
        return buildCreateOrderResponse(customerOrder, items);
    }

    @Override
    public CustomerOrder findBy(Long id) {
        return customerOrderRepository.findById(id)
                .orElseThrow(()->new OrderNotFoundException("order with id "+id+" not found"));
    }

    private CreateOrderResponse buildCreateOrderResponse(CustomerOrder customerOrder, List<OrderLineItem> items){
        CreateOrderResponse response = new CreateOrderResponse();
        response.setOrderId(customerOrder.getId());
        response.setItems(items);
        response.setOrderAmount(customerOrder.getAmount());
        return response;
    }


}
