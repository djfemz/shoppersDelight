package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.CreatePaymentRequest;
import africa.semicolon.shoppersDelight.dtos.response.ApiResponse;
import africa.semicolon.shoppersDelight.dtos.response.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;
    @Test
    public void testProcessPayment(){
        ApiResponse<?> response = paymentService.makePaymentFor(2L);
        log.info("res-->{}", response);
        assertThat(response).isNotNull();
    }
}
