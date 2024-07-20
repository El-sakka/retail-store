package org.retail.store.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.retail.store.exception.ItemNotFoundException;
import org.retail.store.mapper.ItemMapper;
import org.retail.store.model.ItemFixture;
import org.retail.store.model.dto.ItemDto;
import org.retail.store.model.entity.Item;
import org.retail.store.model.request.ItemRequest;
import org.retail.store.repository.ItemRepository;
import org.retail.store.service.impl.ItemServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    private ItemMapper itemMapper = ItemMapper.INSTANCE;

    @Test
    void testAddItem() {
        ItemRequest itemRequest = ItemFixture.getItemRequest();
        Item item = ItemFixture.getItem();

        Item savedItem = Item.builder()
                             .id(1L)
                             .name(itemRequest.getName())
                             .price(itemRequest.getPrice())
                             .isGrocery(itemRequest.isGrocery())
                             .build();

        ItemDto itemDto = ItemFixture.getItemDto();
        when(itemRepository.save(any(Item.class))).thenReturn(savedItem);

        ItemDto result = itemService.addItem(itemRequest);

        assertEquals(itemDto,
                     result);
        verify(itemRepository,
               times(1)).save(any(Item.class));
    }

    @Test
    void testGetItemWhenItemExists() {
        Item item = ItemFixture.getItem();

        ItemDto itemDto = ItemFixture.getItemDto();
        when(itemRepository.findById(anyLong())).thenReturn(Optional.of(item));

        ItemDto result = itemService.getItem(1L);

        assertEquals(itemDto,
                     result);
        verify(itemRepository,
               times(1)).findById(anyLong());
    }

    @Test
    void testGetItemWhenItemDoesNotExist() {
        when(itemRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class,
                     () -> itemService.getItem(1L));
        verify(itemRepository,
               times(1)).findById(anyLong());
    }

}
