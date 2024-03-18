package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddProductRequest;
import africa.semicolon.shoppersDelight.dtos.request.StoreRegistrationRequest;
import africa.semicolon.shoppersDelight.dtos.request.UpdateProductRequest;
import africa.semicolon.shoppersDelight.dtos.response.AddProductResponse;
import africa.semicolon.shoppersDelight.dtos.response.StoreRegistrationResponse;
import africa.semicolon.shoppersDelight.dtos.response.UpdateProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static africa.semicolon.shoppersDelight.models.Category.GROCERIES;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class StoreServiceTest {
   @Autowired
    StoreService storeService;
    @Test
    void registerTest(){
        StoreRegistrationRequest request = new StoreRegistrationRequest();
        request.setCompanyName("shopperDelight");
        request.setEmail("shoppersDelight@gmail.com");
        request.setPassword("password");

        StoreRegistrationResponse response = storeService.register(request);
        assertNotNull(response);
        assertNotNull(response.getId());
    }
    @Test
    void addProductTest() throws Exception {
        AddProductRequest request = new AddProductRequest();
        request.setName("name");
        request.setId(1L);
        request.setPrice(BigDecimal.ONE);
        request.setQuantity(1);
        request.setCategory("electronics");
        request.setDescription("this product is nice");
        AddProductResponse response = storeService.addProduct(request);
        assertNotNull(response);
    }

    @Test
    void updateProduct() throws Exception {
        UpdateProductRequest request = new UpdateProductRequest();
        request.setStoreId(1L);
        request.setProductId(1L);
        request.setProductCategory(GROCERIES);
        UpdateProductResponse response = storeService.updateProduct(request);
        assertNotNull(response);
    }

}
