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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShoppersDelightCartService implements CartService{
   private final CartRepository cartRepository;
   private final ItemService itemService;
   private final ProductService productService;
   private final ModelMapper mapper = new ModelMapper();
    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    @Override
    public AddToCartResponse addToCart(AddToCartRequest request) throws CartNotFoundException {
        Cart cart = findCartBy(request.getCartId());
        Item item = createItem(request);
        cart.getItems().add(item);
        cartRepository.save(cart);
        AddToCartResponse response = new AddToCartResponse();
        response.setResponse("Successfully added item to cart");
        response.setItemId(item.getId());
        return response;
    }

    @Override
    public RemoveItemResponse removeItem(RemoveItemFromCartRequest request) throws CartNotFoundException, ItemNotFoundException {
        RemoveItemResponse removeItemResponse = new RemoveItemResponse();
        Cart cart = findCartBy(request.getCartId());
        List<Item> items = cart.getItems().stream()
                .filter((item) -> !item.getId().equals(request.getItemId()))
                .toList();
        cart.setItems(items);
        cartRepository.save(cart);
        itemService.removeItem(request.getItemId());
        removeItemResponse.setMessage("Item remove from cart");
        return removeItemResponse;
    }

    @Override
    public Cart getCartBy(long id) throws CartNotFoundException {
        return findCartBy(id);
    }

    private  Item createItem(AddToCartRequest request) {
        Item item = new Item();
        var foundProduct = productService.getProductBy(request.getProductId());
        item.setName(foundProduct.getName());
        item.setPrice(foundProduct.getPrice());
        item.setQuantity(foundProduct.getQuantity());
        return item;
    }

    private Cart findCartBy(Long cartId) throws CartNotFoundException {
        return cartRepository.findById(cartId).orElseThrow(()-> new  CartNotFoundException("cart does not exist"));
    }


}
