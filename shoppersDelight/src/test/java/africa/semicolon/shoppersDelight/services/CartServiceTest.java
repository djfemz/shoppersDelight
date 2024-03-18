package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddToCartRequest;
import africa.semicolon.shoppersDelight.dtos.request.RemoveItemFromCartRequest;
import africa.semicolon.shoppersDelight.dtos.response.AddToCartResponse;
import africa.semicolon.shoppersDelight.dtos.response.RemoveItemResponse;
import africa.semicolon.shoppersDelight.exceptions.CartNotFoundException;
import africa.semicolon.shoppersDelight.models.Cart;
import africa.semicolon.shoppersDelight.models.Item;
import africa.semicolon.shoppersDelight.models.Product;
import africa.semicolon.shoppersDelight.repositories.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CartServiceTest {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartService cartService;



    @Test
    public void testThatCanCreateCart(){
        Cart cart = cartService.createCart();
        assertNotNull(cart);
    }
    @Test
    public void testThatCartHasAListOfProduct(){
        Cart cart = cartService.createCart();
        List<Item> productList = cart.getListOfItem();
        assertThat(productList.size()).isEqualTo(0);
    }

    @Test
    public void testThatCreateTwoCartAndCartRepositoryCountIsTwo(){
        Long count = cartRepository.count();
        Cart cart = cartService.createCart();
        Cart cart1 = cartService.createCart();
        assertEquals(count+ 2,cartRepository.count());
    }
    @Test
    public void testThatCanAddToCart() throws CartNotFoundException {
        AddToCartRequest request = new AddToCartRequest();
        Cart cart = cartService.createCart();
        Product product = new Product();
        product.setName("bag");
        product.setPrice(BigDecimal.valueOf(500));
        product.setDescription("bag of money");
        product.setQuantity(2);
        request.setProduct(product);
        request.setCartId(cart.getId());
        AddToCartResponse response =cartService.addToCart(request);
        cart = cartRepository.findById(cart.getId()).get();
        assertThat(cart.getListOfItem().size()).isEqualTo(1);
        assertNotNull(response);
    }
@Test
public void testThatCanAddTwoItemsToCart() throws CartNotFoundException {
    AddToCartRequest request = new AddToCartRequest();
    Cart cart = cartService.createCart();
    Product product = new Product();
    product.setName("bag");
    product.setPrice(BigDecimal.valueOf(500));
    product.setDescription("bag of money");
    product.setQuantity(2);
    request.setProduct(product);
    request.setCartId(cart.getId());
    AddToCartResponse response = cartService.addToCart(request);
    cartService.addToCart(request);
    cart = cartRepository.findById(cart.getId()).get();
    assertThat(cart.getListOfItem().size()).isEqualTo(2);
    assertNotNull(response);
    }
@Test
    public void testThatCanRemoveProductFromCart() throws CartNotFoundException {
    AddToCartRequest addToCartRequest = new AddToCartRequest();
    Cart cart = cartService.createCart();
    Product product = new Product();
    product.setName("bag");
    product.setPrice(BigDecimal.valueOf(500));
    product.setDescription("bag of money");
    product.setQuantity(2);
    addToCartRequest.setProduct(product);
    addToCartRequest.setCartId(cart.getId());
    AddToCartResponse response = cartService.addToCart(addToCartRequest);
    RemoveItemFromCartRequest request = new RemoveItemFromCartRequest();
    request.setCartId(cart.getId());
    request.setItemId(response.getItemId());
    RemoveItemResponse removeItemResponse = cartService.removeItem(request);

}
}

