package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddToCartRequest;
import africa.semicolon.shoppersDelight.dtos.request.RemoveItemFromCartRequest;
import africa.semicolon.shoppersDelight.dtos.response.AddToCartResponse;
import africa.semicolon.shoppersDelight.dtos.response.RemoveItemResponse;
import africa.semicolon.shoppersDelight.exceptions.CartNotFoundException;
import africa.semicolon.shoppersDelight.exceptions.ItemNotFoundException;
import africa.semicolon.shoppersDelight.models.Cart;
import africa.semicolon.shoppersDelight.models.Item;
import africa.semicolon.shoppersDelight.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppCartService implements CartService{
   private final CartRepository cartRepository;
   private final ItemService itemService;
    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    @Override
    public AddToCartResponse addToCart(AddToCartRequest request) throws CartNotFoundException {
        Cart cart = findCartBy(request.getCartId());
        Item item = createItem(request);
        itemService.save(item);
        cart.getListOfItem().add(item);
        cartRepository.save(cart);
        AddToCartResponse response = new AddToCartResponse();
        response.setResponse("Successfully item with  added to cart");
        response.setItemId(item.getId());
        return response;
    }

    @Override
    public RemoveItemResponse removeItem(RemoveItemFromCartRequest request) throws CartNotFoundException, ItemNotFoundException {
        RemoveItemResponse removeItemResponse = new RemoveItemResponse();
        Cart cart = findCartBy(request.getCartId());
        List<Item> items = cart.getListOfItem().stream()
                .filter((item) -> !item.getId().equals(request.getItemId()))
                .toList();
        cart.setListOfItem(items);
        cartRepository.save(cart);
        System.out.println(findCartBy(request.getCartId()));
        itemService.removeItem(request.getItemId());
        removeItemResponse.setMessage("Item remove from cart");
        return removeItemResponse;
    }

    private static Item createItem(AddToCartRequest request) {
        Item item = new Item();
        item.setName(request.getProduct().getName());
        item.setPrice(request.getProduct().getPrice());
        item.setQuantity(request.getProduct().getQuantity());
        return item;
    }

    private Cart findCartBy(Long cartId) throws CartNotFoundException {
        return cartRepository.findById(cartId).orElseThrow(()-> new  CartNotFoundException("cart does not exist"));
    }


}
