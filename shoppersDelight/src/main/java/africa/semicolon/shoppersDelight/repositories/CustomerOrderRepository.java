package africa.semicolon.shoppersDelight.repositories;

import africa.semicolon.shoppersDelight.models.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
}
