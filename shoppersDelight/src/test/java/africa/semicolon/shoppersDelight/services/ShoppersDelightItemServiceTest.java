package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.dtos.response.ItemResponse;
import africa.semicolon.shoppersDelight.models.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ShoppersDelightItemServiceTest {


    @Autowired
    private ItemService itemService;

    @Test
    public void getAllItemsByIdsTest(){
        List<Long> ids = List.of(200L, 201L);
        List<ItemResponse> items = itemService.getAllItems(ids);
        assertThat(items).hasSize(2);
    }
}