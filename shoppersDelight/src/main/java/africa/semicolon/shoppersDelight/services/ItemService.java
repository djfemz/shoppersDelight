package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.response.ItemResponse;
import africa.semicolon.shoppersDelight.exceptions.ItemNotFoundException;
import africa.semicolon.shoppersDelight.models.Item;

import java.util.List;

public interface ItemService {

    void save(Item item);


    Item findById(Long itemId) throws ItemNotFoundException;

    Item removeItem(Long itemId) throws ItemNotFoundException;


    List<ItemResponse> getAllItems(List<Long> ids);
}

