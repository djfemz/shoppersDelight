package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.request.AddToCartRequest;
import africa.semicolon.shoppersDelight.dtos.request.RemoveItemFromCartRequest;
import africa.semicolon.shoppersDelight.dtos.response.AddToCartResponse;
import africa.semicolon.shoppersDelight.dtos.response.RemoveItemResponse;
import africa.semicolon.shoppersDelight.exceptions.CartNotFoundException;
import africa.semicolon.shoppersDelight.exceptions.ItemNotFoundException;
import africa.semicolon.shoppersDelight.models.Cart;

public interface CartService {
    Cart createCart();

    AddToCartResponse addToCart(AddToCartRequest request) throws CartNotFoundException;

    RemoveItemResponse removeItem(RemoveItemFromCartRequest request) throws CartNotFoundException, ItemNotFoundException;


    Cart getCartBy(long id) throws CartNotFoundException;
}


