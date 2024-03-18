package africa.semicolon.shoppersDelight.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemoveItemFromCartRequest {
    private Long CartId;
    private Long ItemId;
}
