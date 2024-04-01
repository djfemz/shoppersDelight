package africa.semicolon.shoppersDelight.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String deliveryAddress;
    private String phoneNumber;
    private String amount;
    @ManyToOne
    private Customer customer;
}
