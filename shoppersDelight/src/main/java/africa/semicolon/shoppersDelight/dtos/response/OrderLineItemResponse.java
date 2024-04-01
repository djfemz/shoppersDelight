package africa.semicolon.shoppersDelight.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class OrderLineItemResponse {
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
