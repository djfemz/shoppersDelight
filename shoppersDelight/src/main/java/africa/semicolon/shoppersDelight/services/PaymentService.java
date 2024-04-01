package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.response.ApiResponse;
import africa.semicolon.shoppersDelight.dtos.response.PaymentResponse;

public interface PaymentService {
    ApiResponse<?> makePaymentFor(Long orderId);
}
