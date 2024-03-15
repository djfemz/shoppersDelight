package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.models.Cart;
import africa.semicolon.shoppersDelight.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppCartService implements CartService{
   private final CartRepository cartRepository;
    @Override
    public Cart createCart() {
    Cart cart = new Cart();
    cartRepository.save(cart);
        return cart;
    }
}
