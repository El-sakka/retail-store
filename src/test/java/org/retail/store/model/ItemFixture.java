package org.retail.store.model;

import org.retail.store.model.dto.ItemDto;
import org.retail.store.model.entity.Item;
import org.retail.store.model.request.ItemRequest;

import java.util.ArrayList;
import java.util.List;

public class ItemFixture {

    public static Item getItem() {
        return Item.builder()
                .name("apple")
                .price(2.0)
                .isGrocery(true)
                .build();
    }

    public static ItemRequest getItemRequest(){
        return ItemRequest.builder()
                   .name("apple")
                   .price(2.0)
                   .isGrocery(true)
                   .build();
    }

    public static ItemDto getItemDto(){
        return ItemDto.builder()
                          .name("apple")
                          .price(2.0)
                          .build();
    }

    public static List<ItemRequest> getItemRequests(){
        List<ItemRequest> itemRequests = new ArrayList<>();

        itemRequests.add(new ItemRequest("A", 100, false));
        itemRequests.add(new ItemRequest("B", 10, true));
        itemRequests.add(new ItemRequest("C", 600, false));
        itemRequests.add(new ItemRequest("D", 20, true));
        itemRequests.add(new ItemRequest("E", 500, false));
        itemRequests.add(new ItemRequest("F", 300, false));
        itemRequests.add(new ItemRequest("G", 200, false));
        itemRequests.add(new ItemRequest("H", 50, false));

        return itemRequests;
    }

    public static List<Item> getItemsData(){
        List<Item> items = new ArrayList<>();

        items.add(new Item(1L,"A", 100, false));
        items.add(new Item(2L,"B", 10, true));
        items.add(new Item(3L,"C", 600, false));
        items.add(new Item(4L,"D", 20, true));
        items.add(new Item(5L,"E", 500, false));
        items.add(new Item(6L,"F", 300, false));
        items.add(new Item(7L,"G", 200, false));
        items.add(new Item(8L,"H", 50, false));
        return items;
    }
}
