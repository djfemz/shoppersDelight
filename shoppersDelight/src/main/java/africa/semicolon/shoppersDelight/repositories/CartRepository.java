package africa.semicolon.shoppersDelight.repositories;

import africa.semicolon.shoppersDelight.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
