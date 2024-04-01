package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.response.ItemResponse;
import africa.semicolon.shoppersDelight.exceptions.ItemNotFoundException;
import africa.semicolon.shoppersDelight.models.Item;
import africa.semicolon.shoppersDelight.repositories.ItemRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShoppersDelightItemService implements ItemService {
    private final ItemRepository itemRepository;
    private final ModelMapper mapper;

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

    @Override
    public List<ItemResponse> getAllItems(List<Long> ids) {
        return itemRepository.findAllById(ids)
                .stream()
                .map(item->mapper.map(item, ItemResponse.class))
                .toList();
    }
}
