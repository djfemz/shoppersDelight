package africa.semicolon.shoppersDelight.repositories;

import africa.semicolon.shoppersDelight.models.OrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {
}
