package africa.semicolon.shoppersDelight.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class NotificationShoppers {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String message;
}
