package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddProductRequest;
import africa.semicolon.shoppersDelight.dtos.response.AddProductResponse;
import africa.semicolon.shoppersDelight.dtos.response.ProductResponse;
import africa.semicolon.shoppersDelight.models.Store;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void addProductTest() throws Exception {
        AddProductRequest request = new AddProductRequest();
        request.setName("chocolate");
        request.setPrice(BigDecimal.TEN);
        request.setDescription("yummy, yum, yum!");
        request.setQuantity(10);
        request.setCategory("GROCERIES");
        Store store = new Store();
        store.setEmail("email");

        AddProductResponse response= productService.addProduct(request,store);
        log.info("product added :: {}", response);
        assertThat(response).isNotNull();
    }
    @Test
    @Sql(scripts = {"/scripts/insert.sql"})
    public void getProductTest(){
        ProductResponse product = productService.getProductBy(203L);
        log.info("found product--->{}", product);
        assertThat(product).isNotNull();
    }
    @Test
    @Sql(scripts = {"/scripts/insert.sql"})
    public void getProductsTest(){
        List<ProductResponse> products = productService.getProducts(1, 1);
        assertThat(products).hasSize(1);
    }
}
