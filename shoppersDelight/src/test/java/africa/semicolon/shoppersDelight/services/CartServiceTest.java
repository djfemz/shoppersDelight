package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddToCartRequest;
import africa.semicolon.shoppersDelight.dtos.request.RemoveItemFromCartRequest;
import africa.semicolon.shoppersDelight.dtos.response.AddToCartResponse;
import africa.semicolon.shoppersDelight.dtos.response.ProductResponse;
import africa.semicolon.shoppersDelight.dtos.response.RemoveItemResponse;
import africa.semicolon.shoppersDelight.exceptions.CartNotFoundException;
import africa.semicolon.shoppersDelight.exceptions.ItemNotFoundException;
import africa.semicolon.shoppersDelight.models.Cart;
import africa.semicolon.shoppersDelight.models.Item;
import africa.semicolon.shoppersDelight.repositories.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

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
    @Autowired
    private ProductService productService;




    @Test
    public void testThatCanCreateCart(){
        Cart cart = cartService.createCart();
        assertNotNull(cart);
    }
    @Test
    public void testThatCartHasAListOfProduct(){
        Cart cart = cartService.createCart();
        List<Item> productList = cart.getItems();
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

        List<ProductResponse> products = productService.getProducts(1, 5);
        Long productId = products.get(products.size() - 1).getId();
        request.setProductId(productId);
        request.setCartId(cart.getId());
        AddToCartResponse response =cartService.addToCart(request);
        cart = cartRepository.findById(cart.getId()).get();
        assertThat(cart.getItems().size()).isEqualTo(1);
        assertNotNull(response);
    }
@Test
public void testThatCanAddTwoItemsToCart() throws CartNotFoundException {
    AddToCartRequest request = new AddToCartRequest();
    Cart cart = cartService.createCart();

    List<ProductResponse> products = productService.getProducts(1, 5);
    Long productId = products.get(products.size() - 1).getId();
    request.setProductId(productId);
    request.setCartId(cart.getId());
    AddToCartResponse response = cartService.addToCart(request);
    cartService.addToCart(request);
    cart = cartRepository.findById(cart.getId()).get();
    assertThat(cart.getItems().size()).isEqualTo(2);
    assertNotNull(response);
    }

    @Test
    @Sql(scripts = {"/scripts/insert.sql"})
    public void getCartByIdTest() throws CartNotFoundException {
        assertThat(cartService.getCartBy(200L)).isNotNull();
    }
    @Test
    @Sql(scripts = {"/scripts/insert.sql"})
    public void testThatCanRemoveItemFromCart() throws CartNotFoundException, ItemNotFoundException {
        Cart cart = cartService.createCart();

        RemoveItemFromCartRequest request = new RemoveItemFromCartRequest();
        request.setCartId(cart.getId());
        request.setItemId(200L);
        RemoveItemResponse removeItemResponse = cartService.removeItem(request);
        assertNotNull(removeItemResponse);

    }
}

