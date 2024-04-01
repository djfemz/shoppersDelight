package africa.semicolon.shoppersDelight.repositories;

import africa.semicolon.shoppersDelight.models.Cart;
import africa.semicolon.shoppersDelight.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c from Customer c where c.cart=:cart")
    Optional<Customer> findByCart(Cart cart);

}
