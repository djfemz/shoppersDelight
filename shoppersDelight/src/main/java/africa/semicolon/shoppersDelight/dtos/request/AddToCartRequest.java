package africa.semicolon.shoppersDelight.dtos.request;

import africa.semicolon.shoppersDelight.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddToCartRequest {
    private Long productId;
    private Long CartId;
}
