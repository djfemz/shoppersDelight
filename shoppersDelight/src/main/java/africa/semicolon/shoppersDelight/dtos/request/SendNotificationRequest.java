package africa.semicolon.shoppersDelight.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendNotificationRequest {

    private Long customerId;
    private String message;

}
