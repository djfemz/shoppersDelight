package africa.semicolon.shoppersDelight.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SendNotificationRequest {
    private Long customerId;
    private String message;

}
