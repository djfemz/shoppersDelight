package africa.semicolon.shoppersDelight.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private List<Item> listOfItem = new ArrayList<>();


}
