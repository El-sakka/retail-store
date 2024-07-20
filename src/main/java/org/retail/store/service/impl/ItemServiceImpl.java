package org.retail.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.retail.store.exception.ItemNotFoundException;
import org.retail.store.mapper.ItemMapper;
import org.retail.store.model.dto.ItemDto;
import org.retail.store.model.entity.Item;
import org.retail.store.model.request.ItemRequest;
import org.retail.store.repository.ItemRepository;
import org.retail.store.service.ItemService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    ItemMapper itemMapper = ItemMapper.INSTANCE;

    @Override
    public ItemDto addItem(ItemRequest itemRequest) {

        Item item = Item.builder()
                .name(itemRequest.getName())
                .price(itemRequest.getPrice())
                .isGrocery(itemRequest.isGrocery())
                .build();
        item = itemRepository.save(item);
        return itemMapper.toItemDto(item);
    }

    @Override
    public ItemDto getItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        return itemMapper.toItemDto(item);
    }
}
