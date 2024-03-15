package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddProductRequest;
import africa.semicolon.shoppersDelight.dtos.request.AddProductToCartRequest;
import africa.semicolon.shoppersDelight.models.Cart;
import africa.semicolon.shoppersDelight.models.Product;
import africa.semicolon.shoppersDelight.repositories.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CartServiceTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Test
    public void testThatCanCreateCart(){
        Cart cart = cartService.createCart();
        assertNotNull(cart);
    }
    @Test
    public void testThatCartHasAListOfProduct(){
        Cart cart = cartService.createCart();
        List<Product> productList = cart.getListOfProduct();
        assertThat(productList.size()).isEqualTo(0);
    }

    @Test
    public void testThatCreateTwoCartAndCartRepositoryCountIsTwo(){
        Cart cart = cartService.createCart();
        Cart cart1 = cartService.createCart();
        assertEquals(2,cartRepository.count());

    }

}
