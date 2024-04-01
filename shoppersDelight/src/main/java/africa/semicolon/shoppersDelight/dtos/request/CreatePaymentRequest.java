package africa.semicolon.shoppersDelight.dtos.request;

import lombok.Setter;

import java.math.BigDecimal;

@Setter
public class CreatePaymentRequest {
    private BigDecimal amount;
    private Long orderId;
}
