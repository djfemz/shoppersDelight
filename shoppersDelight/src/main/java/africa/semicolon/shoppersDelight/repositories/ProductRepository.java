package africa.semicolon.shoppersDelight.repositories;

import africa.semicolon.shoppersDelight.models.Category;
import africa.semicolon.shoppersDelight.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findDistinctByProductCategory(Category category);
}
