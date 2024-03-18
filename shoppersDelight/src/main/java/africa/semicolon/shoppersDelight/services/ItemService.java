package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.exceptions.ItemNotFoundException;
import africa.semicolon.shoppersDelight.models.Item;

public interface ItemService {

    void save(Item item);


    Item findById(Long itemId) throws ItemNotFoundException;


}

