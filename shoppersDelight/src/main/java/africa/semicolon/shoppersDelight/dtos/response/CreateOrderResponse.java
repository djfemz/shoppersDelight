package africa.semicolon.shoppersDelight.dtos.response;


import africa.semicolon.shoppersDelight.models.OrderLineItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateOrderResponse {
    private Long orderId;
    private List<OrderLineItem> items;
    private String orderAmount;
}
