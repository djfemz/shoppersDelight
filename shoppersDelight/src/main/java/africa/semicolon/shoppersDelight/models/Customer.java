package africa.semicolon.shoppersDelight.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL ,fetch =FetchType.EAGER)
    private List<NotificationShoppers> notificationShoppers;
}
