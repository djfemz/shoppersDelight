package africa.semicolon.shoppersDelight.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    private Cart cart;
    @OneToMany(fetch = FetchType.EAGER)
    @Cascade({CascadeType.MERGE,CascadeType.PERSIST})
    private List<NotificationShoppers> notificationShoppers;
}
