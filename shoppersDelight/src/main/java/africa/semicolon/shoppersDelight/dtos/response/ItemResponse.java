package africa.semicolon.shoppersDelight.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class ItemResponse {
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
