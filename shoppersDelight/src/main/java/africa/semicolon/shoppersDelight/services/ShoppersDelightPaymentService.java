package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.config.BeanConfig;
import africa.semicolon.shoppersDelight.dtos.request.InitializeTransactionRequest;
import africa.semicolon.shoppersDelight.dtos.response.ApiResponse;
import africa.semicolon.shoppersDelight.dtos.response.PaystackTransactionResponse;
import africa.semicolon.shoppersDelight.models.CustomerOrder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.http.HttpMethod.POST;

@Service
@AllArgsConstructor
@Slf4j
public class ShoppersDelightPaymentService implements PaymentService{
    private final OrderService orderService;
    private final BeanConfig beanConfig;
    @Override
    public ApiResponse<?> makePaymentFor(Long orderId) {
        RestTemplate restTemplate = new RestTemplate();
        CustomerOrder foundOrder = orderService.findBy(orderId);
        HttpEntity<InitializeTransactionRequest> request = buildPaymentRequest(foundOrder);
        ResponseEntity<PaystackTransactionResponse> response =
                restTemplate.postForEntity(beanConfig.getPaystackBaseUrl(), request, PaystackTransactionResponse.class);
        return new ApiResponse<>(response.getBody());
    }

    private HttpEntity<InitializeTransactionRequest> buildPaymentRequest(CustomerOrder foundOrder) {
        InitializeTransactionRequest transactionRequest = new InitializeTransactionRequest();
        transactionRequest.setEmail(foundOrder.getCustomer().getEmail());
        transactionRequest.setAmount(foundOrder.getAmount());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer "+beanConfig.getPaystackApiKey());
        return new HttpEntity<>(transactionRequest, headers);
    }
}
