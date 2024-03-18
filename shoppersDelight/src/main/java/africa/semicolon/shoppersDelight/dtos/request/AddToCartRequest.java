package africa.semicolon.shoppersDelight.dtos.request;

import africa.semicolon.shoppersDelight.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddToCartRequest {
    private Product product;
    private Long CartId;
}
