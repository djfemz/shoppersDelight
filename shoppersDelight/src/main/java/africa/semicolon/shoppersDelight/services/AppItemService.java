package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.response.RemoveItemResponse;
import africa.semicolon.shoppersDelight.exceptions.ItemNotFoundException;
import africa.semicolon.shoppersDelight.models.Item;
import africa.semicolon.shoppersDelight.repositories.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppItemService implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

        @Override
    public Item findById(Long itemId) throws ItemNotFoundException {
        return itemRepository.findById(itemId).orElseThrow(()->new ItemNotFoundException("Item not found"));
    }
    @Override
    public Item removeItem(Long itemId) throws ItemNotFoundException {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item not found"));
        itemRepository.delete(item);
        return item;
    }
}
