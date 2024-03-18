package africa.semicolon.shoppersDelight.repositories;

import africa.semicolon.shoppersDelight.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
