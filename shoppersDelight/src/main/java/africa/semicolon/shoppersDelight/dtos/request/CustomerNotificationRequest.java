package africa.semicolon.shoppersDelight.dtos.request;

import africa.semicolon.shoppersDelight.models.Customer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerNotificationRequest {

    private Long id;
    private String message;

}
