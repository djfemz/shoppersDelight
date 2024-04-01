package africa.semicolon.shoppersDelight.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {
    private List<Long> items;
    private String deliveryAddress;
    private String phoneNumber;
    private Long cartId;
}
