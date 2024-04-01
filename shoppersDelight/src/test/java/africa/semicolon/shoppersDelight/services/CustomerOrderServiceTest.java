package africa.semicolon.shoppersDelight.services;


import africa.semicolon.shoppersDelight.dtos.request.CreateOrderRequest;
import africa.semicolon.shoppersDelight.dtos.response.CreateOrderResponse;
import africa.semicolon.shoppersDelight.exceptions.CartNotFoundException;
import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class CustomerOrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrderService() throws CustomerNotFoundException, CartNotFoundException {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setItems(List.of(200L, 201L));
        request.setDeliveryAddress("312, Herbert Macaulay Way, Yaba");
        request.setPhoneNumber("08120582044");
        request.setCartId(200L);

        CreateOrderResponse response = orderService.create(request);
        log.info("response-->{}", response);
        assertThat(response).isNotNull();
    }
}
